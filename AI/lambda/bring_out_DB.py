import json
import openai
import pymysql
import os

# OpenAI API 키 설정
OPENAI_API_KEY = os.getenv('OPENAI_API_KEY')
openai.api_key = OPENAI_API_KEY

# RDS 설정 (주석 처리됨)
# rds_host = os.environ.get('RDS_HOST')
# rds_user = os.environ.get('RDS_USER')
# rds_password = os.environ.get('RDS_PASSWORD')
# rds_db_name = os.environ.get('RDS_DB_NAME')

# Azure MySQL 설정
azure_host = os.environ.get('AZURE_HOST')
azure_user = os.environ.get('AZURE_USER')
azure_password = os.environ.get('AZURE_PASSWORD')
azure_db_name = os.environ.get('AZURE_DB_NAME')

def get_user_health_data(user_id):
    connection = pymysql.connect(host=azure_host, user=azure_user, password=azure_password, database=azure_db_name)
    try:
        with connection.cursor(pymysql.cursors.DictCursor) as cursor:
            # 건강 데이터 가져오기
            sql = """
            SELECT 
                hc_date, hc_height, hc_weight, memberpk, hc_bodyfatmass, 
                hc_minerals, hc_protein, hc_purpose, 
                hc_skeletalmusclemass, hc_totalbodywater, hc_job, hc_sex
            FROM hc
            WHERE memberpk = %s
            """
            cursor.execute(sql, (user_id,))
            user_data = cursor.fetchone()
            
            return user_data
    finally:
        connection.close()

def update_user_report(user_id, report):
    connection = pymysql.connect(host=azure_host, user=azure_user, password=azure_password, database=azure_db_name)
    try:
        with connection.cursor() as cursor:
            # report 업데이트
            sql = """
            UPDATE hc
            SET hc_report = %s
            WHERE memberpk = %s
            """
            cursor.execute(sql, (report, user_id))
            connection.commit()
    finally:
        connection.close()

def lambda_handler(event, context):
    try:
        # 요청 본문에서 user_id 추출
        body = json.loads(event['body'])
        user_id = body['user_id']
        # user_id를 사용하여 Azure MySQL에서 데이터 가져오기
        user_data = get_user_health_data(user_id)
        if not user_data:
            raise ValueError("해당 user_id에 대한 데이터를 찾을 수 없습니다.")

        # user_data에서 필요한 정보 추출
        user_name = user_id
        job = user_data.get('hc_job', 'N/A')
        height = user_data['hc_height']
        weight = user_data['hc_weight']
        age = user_data.get('age', 'N/A')  # age 필드가 없을 경우 'N/A'
        purpose = user_data['hc_purpose']
        total_body_water = user_data['hc_totalbodywater']
        protein = user_data['hc_protein']
        minerals = user_data['hc_minerals']
        body_fat_mass = user_data['hc_bodyfatmass']
        skeletal_muscle_mass = user_data['hc_skeletalmusclemass']
        date = user_data['hc_date']

        # 프롬프트 엔지니어링
        prompt = f"""
        너는 헬스 트레이너야. 주어진 데이터를 바탕으로 다음 질문에 답변해줘:

        - 사용자의 현재 건강 상태는 어떤가?
        - 사용자의 신체정보와 운동 목적에 맞는 운동 프로그램은 무엇이 좋을까?
        - 사용자의 직업이나 상태에 맞는 식단 추천도 해줄 수 있을까?

        사용자 정보:
        - 이름: {user_name}
        - 직업: {job}

        신체 정보:
        - 키: {height} cm
        - 몸무게: {weight} kg
        - 나이: {age}

        추가 정보:
        - 운동 목적: {purpose}
        - 체수분: {total_body_water}%
        - 단백질: {protein}%
        - 무기질: {minerals}%
        - 체지방: {body_fat_mass}%
        - 골격근량: {skeletal_muscle_mass}%
        - 측정 날짜: {date}
        """

        messages = [
            {"role": "system", "content": "너는 헬스 트레이너입니다. 사용자의 건강 데이터를 분석하고 아래 질문에 답변해 주세요:"},
            {"role": "user", "content": prompt}
        ]

        # ChatGPT API 호출 및 로깅
        response = openai.ChatCompletion.create(
            model="gpt-3.5-turbo",
            messages=messages,
            max_tokens=768,
            temperature=0
        )
        response_message = response['choices'][0]['message']['content']

        # 결과값을 HTML 형식으로 포맷팅
        formatted_response = response_message.replace("\n", "<br>")
        
        update_user_report(user_id, formatted_response)
        
        return {
            'statusCode': 200,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps({"response": formatted_response})
        }

    except ValueError as e:
        print("ValueError:", e)
        return {
            'statusCode': 400,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps({'message': str(e)})
        }
    except Exception as e:
        print("Exception:", e)
        return {
            'statusCode': 500,
            'headers': {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Content-Type',
                'Access-Control-Allow-Methods': 'OPTIONS,POST,GET'
            },
            'body': json.dumps({'message': 'Internal server error', 'error': str(e)})
        }
