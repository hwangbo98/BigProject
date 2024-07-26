import json
import boto3
import os
import requests

def lambda_handler(event, context):
    s3 = boto3.client('s3')
    rekognition = boto3.client('rekognition')
    
    # Rekognition Collection ID
    collection_id = 'employees-faces'
    
    # Slack Webhook URL
    webhook_url = 'https://hooks.slack.com/services/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'
    
    if 'Records' not in event:
        return {
            'statusCode': 400,
            'body': json.dumps('No Records found in event')
        }

    print(event['Records'])
    # 이벤트 레코드 처리
    for record in event['Records']:
        bucket = record['s3']['bucket']['name']
        key = record['s3']['object']['key']
        event_name = record['eventName']
        
        # 파일 이름에서 확장자 제거
        img_id = os.path.splitext(os.path.basename(key))[0]
        
        if 'ObjectCreated' in event_name:
            # 이미지 추가
            response = rekognition.index_faces(
                CollectionId=collection_id,
                Image={
                    'S3Object': {
                        'Bucket': bucket,
                        'Name': key
                    }
                },
                DetectionAttributes=['ALL'],
                ExternalImageId=img_id  # 확장자를 제거한 파일 이름 사용
            )
            print(f"Image added to Rekognition Collection: {response}")
            
            # Slack에 알림 전송
            text = f":camera: {img_id}님의 이미지가 Rekognition Collection에 추가되었습니다 :tada:"
            slack_message = {
                "channel": "#사진-업로드",
                "username": "Upload-image",
                "text": text,
                "icon_url": "https://avatars.slack-edge.com/2024-07-05/7392202473633_03061b4caa4a7d8832d5_48.webp"  # 사용자 지정 아이콘 URL
            }
            response = requests.post(
                webhook_url, 
                data=json.dumps(slack_message),
                headers={'Content-Type': 'application/json'}
            )
            if response.status_code != 200:
                print(f'Request to Slack returned an error {response.status_code}, the response is:\n{response.text}')
        
        elif 'ObjectRemoved' in event_name:
            # 이미지 삭제
            face_ids = []
            
            # 컬렉션에서 해당 이미지의 Face ID를 가져옵니다.
            response = rekognition.list_faces(CollectionId=collection_id)
            for face in response['Faces']:
                if face['ExternalImageId'] == img_id:
                    face_ids.append(face['FaceId'])
            
            if face_ids:
                response = rekognition.delete_faces(
                    CollectionId=collection_id,
                    FaceIds=face_ids
                )
                print(f"Image deleted from Rekognition Collection: {response}")
            else:
                print(f"Face ID not found in Rekognition Collection for key: {img_id}")
            
            # Slack에 알림 전송
            text = f":wastebasket: {img_id}님의 이미지가 Rekognition Collection에서 삭제되었습니다 :sob:"
            slack_message = {
                "channel": "#사진-업로드",
                "username": "Upload-image",
                "text": text,
                "icon_url": "https://your-custom-icon-url.com/icon.png"  # 사용자 지정 아이콘 URL
            }
            response = requests.post(
                webhook_url, 
                data=json.dumps(slack_message),
                headers={'Content-Type': 'application/json'}
            )
            if response.status_code != 200:
                print(f'Request to Slack returned an error {response.status_code}, the response is:\n{response.text}')

    return {
        'statusCode': 200,
        'body': json.dumps('Process completed')
    }