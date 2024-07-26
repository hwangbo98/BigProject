import json
import pymysql
import os
from datetime import datetime, date
import logging
import base64
import boto3
import time

logger = logging.getLogger()
logger.setLevel(logging.INFO)

rekognition = boto3.client('rekognition')
REKOGNITION_COLLECTION_ID = 'employees-faces'

# RDS settings
# rds_host = os.environ.get('AWS_RDS_HOST')
# rds_user = os.environ.get('AWS_RDS_USER')
# rds_password = os.environ.get('AWS_RDS_PASSWORD')
# rds_db_name = os.environ.get('AWS_RDS_DB_NAME')

# Azure MySQL settings
azure_host = os.environ.get('AZURE_HOST')
azure_user = os.environ.get('AZURE_USER')
azure_password = os.environ.get('AZURE_PASSWORD')
azure_db_name = os.environ.get('AZURE_DB_NAME')

def connect_to_database(host, user, password, db_name):
    return pymysql.connect(host=host, user=user, passwd=password, db=db_name, connect_timeout=60)

def lambda_handler(event, context):
    try:
        if 'body' not in event:
            raise ValueError("No body found in the event")
        
        body = json.loads(event['body']) if isinstance(event['body'], str) else event['body']
        
        if 'image' not in body:
            raise ValueError('Image not found in the request body')
            
        target_image = body['image']
        logger.info(f'Target image length: {len(target_image)}')
        
        target_image_bytes = base64.b64decode(target_image)
        
        response = rekognition.search_faces_by_image(
            CollectionId=REKOGNITION_COLLECTION_ID,
            Image={'Bytes': target_image_bytes},
            MaxFaces=10,
            FaceMatchThreshold=85
        )
        
        logger.info(f"Rekognition response: {json.dumps(response)}")
        print(response)
        attendance_results = []
        now = datetime.now()
        today_date = date.today().strftime("%Y-%m-%d")

        # rds_connection = connect_to_database(rds_host, rds_user, rds_password, rds_db_name)
        azure_connection = connect_to_database(azure_host, azure_user, azure_password, azure_db_name)
        
        with azure_connection.cursor() as azure_cursor:
            for match in response['FaceMatches']:
                person_name = match['Face']['ExternalImageId']
                
                print(person_name)
                azure_cursor.execute("""
                    SELECT b.memberpk, a.user_nickname 
                    FROM member b 
                    JOIN alluser a ON b.userpk = a.userpk 
                    WHERE a.username=%s
                """, (person_name,))
                
                result = azure_cursor.fetchone()
                if result:
                    memberpk, user_nickname = result
                    
                    azure_cursor.execute("""
                        SELECT create_date FROM attendance 
                        WHERE memberpk=%s AND create_date=%s
                    """, (memberpk, today_date))
                    
                    attendance_check = azure_cursor.fetchone()
                    
                    if attendance_check:
                        attendance_results.append({
                            'message': '이미 출석하였습니다',
                            'user_nickname': user_nickname
                        })
                    else:
                        azure_cursor.execute("""
                            INSERT INTO attendance (create_date, memberpk) VALUES (%s, %s)
                        """, (today_date, memberpk))
                        
                        azure_connection.commit()
                        
                        attendance_results.append({
                            'message': '정상적으로 출석하였습니다',
                            'timestamp': now.isoformat(),
                            'user_nickname': user_nickname
                        })

        time.sleep(5)
        return {
            'statusCode': 200,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps(attendance_results)
        }
        
    except Exception as e:
        logger.error(f"Error: {str(e)}")
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps({'error': str(e)})
        }