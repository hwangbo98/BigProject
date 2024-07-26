import json
import boto3
import requests

import base64
import os

s3 = boto3.client('s3')

def lambda_handler(event, context):
    body = json.loads(event['body'])
    file_name = body['fileName']
    file_type = body['fileType']

    bucket_name = os.environ['S3_BUCKET_NAME']

    try:
        presigned_url = s3.generate_presigned_url('put_object',
                                                  Params={'Bucket': bucket_name,
                                                          'Key': file_name,
                                                          'ContentType': file_type},
                                                  ExpiresIn=3600)
        return {
            'statusCode': 200,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps({'url': presigned_url})
        }
    except Exception as e:
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps({'error': str(e)})
        }