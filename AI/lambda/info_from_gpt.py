import json
import boto3
import base64
import requests
import os
import openai

s3 = boto3.client('s3')
OPENAI_API_KEY = os.getenv('OPENAI_API_KEY')
openai.api_key = OPENAI_API_KEY
def lambda_handler(event, context):
    try:
        # 이벤트 로그 출력
        print(f"Received event: {json.dumps(event)}")

        # HTTP 메서드 확인
        http_method = event.get('httpMethod', '')
        if http_method != 'POST':
            return {
                'statusCode': 405,
                'headers': {
                    'Access-Control-Allow-Origin': '*',  # CORS 헤더 추가
                    'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
                    'Access-Control-Allow-Headers': 'Content-Type'
                },
                'body': json.dumps({'error': 'Method Not Allowed'})
            }

        # 이벤트에서 파일명 가져오기
        if 'body' not in event:
            raise ValueError("Missing 'body' in event")

        body = json.loads(event['body'])
        if 'file_name' not in body:
            raise ValueError("Missing 'file_name' in body")

        file_name = body['file_name']
        bucket_name = os.environ['BUCKET_NAME']
        
        print(f"Fetching file {file_name} from bucket {bucket_name}")

        # S3에서 파일 가져오기
        try:
            data = s3.get_object(Bucket=bucket_name, Key=file_name)
            res = data['Body'].read()
            res = base64.b64encode(res).decode('utf-8')
            print("File fetched and base64 encoded")
        except Exception as e:
            print(f"Error fetching file from S3: {str(e)}")
            raise

        # 환경 변수에서 OCR API 키와 URL 가져오기
        ocr_api_key = os.environ['OCR_API_KEY']
        ocr_url = os.environ['OCR_URL']
        gpt_api_key = os.environ['GPT_API_KEY']
        
        headers = {
            "Content-Type": "application/json",
            "X-OCR-SECRET": ocr_api_key
        }
        
        payload = {
            "version": "V1",
            "requestId": "sample_id",
            "timestamp": 0,
            "images": [
                {
                    "name": "sample_image",
                    "format": "jpg",
                    "data": res
                }
            ]
        }
        
        print(f"Sending payload to OCR API at {ocr_url}")

        # 타임아웃 설정 및 재시도 로직 추가
        try:
            response = requests.post(ocr_url, headers=headers, json=payload, timeout=30)
            response.raise_for_status()
            print("OCR API call successful")
        except requests.exceptions.RequestException as e:
            print(f"OCR API error: {str(e)}")
            raise

        # OCR 결과 파싱
        try:
            ocr_result = response.json()
            extracted_text = ' '.join([field['inferText'] for field in ocr_result['images'][0]['fields']])
            print(f"OCR Result: {extracted_text}")
        except Exception as e:
            print(f"Error parsing OCR result: {str(e)}")
            raise

        # GPT-3.5-turbo를 사용하여 필요한 정보 추출
        try:
            # openai.api_key = gpt_api_key
            
            gpt_response = openai.ChatCompletion.create(
                model="gpt-3.5-turbo",
                messages=[
                    {"role": "system", "content": "You are a helpful assistant."},
                    {"role": "user", "content": f"다음 텍스트에서 회원번호, 성별, 신장, 체중, 나이, 체수분, 단백질, 무기질, 체지방량, 골격근량, 체지방률, 측정날짜를 JSON 형태로 추출해줘. 좀만 정확하게 해줄 수 있을까? 괄호로 적힌 값들은 제외하고, cm, kg 등의 단위는 빼줘 그냥 적힌 값만 가지고 해줘. 측정날짜의 경우에는 yyyy-MM-dd로 표현해줘 : {extracted_text}"}
                ],
                max_tokens=500,
                temperature=0.7
            )
            
            extracted_data = gpt_response.choices[0].message['content'].strip()
            print(f"Extracted data: {extracted_data}")
        except openai.error.OpenAIError as e:
            print(f"GPT API error: {str(e)}")
            raise

        return {
            'statusCode': 200,
            'headers': {
                'Access-Control-Allow-Origin': '*',  # CORS 헤더 추가
                'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps({
                'extracted_data': extracted_data
            })
        }
    except Exception as e:
        print(f"Unexpected error: {str(e)}")
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',  # CORS 헤더 추가
                'Access-Control-Allow-Methods': 'GET, POST, OPTIONS',
                'Access-Control-Allow-Headers': 'Content-Type'
            },
            'body': json.dumps({'error': f'Unexpected error: {str(e)}'})
        }