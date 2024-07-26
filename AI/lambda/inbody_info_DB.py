import json
import pymysql
import os
from datetime import datetime
import logging

logger = logging.getLogger()
logger.setLevel(logging.INFO)

# RDS settings
rds_host = os.environ.get('AWS_RDS_HOST')
rds_user = os.environ.get('AWS_RDS_USER')
rds_password = os.environ.get('AWS_RDS_PASSWORD')
rds_db_name = os.environ.get('AWS_RDS_DB_NAME')

def lambda_handler(event, context):
    logger.info(f"Received event: {json.dumps(event)}")
    
    # JSON 문자열을 파싱하여 event 객체로 변환
    if isinstance(event.get('body'), str):
        event = json.loads(event['body'])
    elif isinstance(event, dict) and 'body' in event:
        event = json.loads(event['body'])
    
    # 날짜 필드가 존재하는지 확인
    if 'date' not in event:
        return {
            'statusCode': 400,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'POST',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps('date 필드가 없습니다.')
        }
    
    # 날짜 형식 변환
    try:
        event['date'] = datetime.strptime(event['date'], "%Y-%m-%d").strftime("%Y-%m-%d")
    except ValueError as ve:
        logger.error(f"Date format error: {str(ve)}")
        return {
            'statusCode': 400,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'POST',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps(f'날짜 형식 오류: {str(ve)}')
        }

    # MySQL 연결
    try:
        connection = pymysql.connect(
            host=rds_host,
            user=rds_user,
            password=rds_password,
            db=rds_db_name,
            charset='utf8mb4',
            cursorclass=pymysql.cursors.DictCursor
        )
    except Exception as e:
        logger.error(f"Database connection error: {str(e)}")
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'POST',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps(f'데이터베이스 연결 오류: {str(e)}')
        }
    
    try:
        with connection.cursor() as cursor:
            # SQL 삽입문
            sql = """
                INSERT INTO hc (
                    hc_date, hc_height, hc_weight, memberpk, hc_bodyfatmass, 
                    hc_minerals, hc_protein, hc_purpose, 
                    hc_skeletalmusclemass, hc_totalbodywater, hc_job, hc_sex
                ) VALUES (
                    %(hc_date)s, %(hc_height)s, %(hc_weight)s, %(memberpk)s, %(hc_bodyfatmass)s, 
                    %(hc_minerals)s, %(hc_protein)s, %(hc_purpose)s, 
                    %(hc_skeletalmusclemass)s, %(hc_totalbodywater)s, %(hc_job)s, %(hc_sex)s
                )
            """
            cursor.execute(sql, {
                'hc_date': event['date'],
                'hc_height': event['height'],
                'hc_weight': event['weight'],
                'memberpk': event['user_id'],
                'hc_bodyfatmass': event['body_fat_mass'],
                'hc_minerals': event['minerals'],
                'hc_protein': event['protein'],
                'hc_purpose': event['purpose'],
                'hc_skeletalmusclemass': event['skeletal_muscle_mass'],
                'hc_totalbodywater': event['total_body_water'],
                'hc_job': event['job'],
                'hc_sex': event['sex']
            })
            connection.commit()
        
        return {
            'statusCode': 200,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'POST',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps('데이터가 성공적으로 저장되었습니다.')
        }
    except Exception as e:
        logger.error(f"Data storage error: {str(e)}")
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'POST',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps(f'데이터 저장 오류: {str(e)}')
        }
    finally:
        connection.close()