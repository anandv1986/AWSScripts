from __future__ import print_function
from datetime import datetime
import json
import boto3
print('Loading function')

def lambda_handler(event, context):
    #print("Received event: " + json.dumps(event, indent=2))
    message = event['Records'][0]['Sns']['Message']
    print("From SNS: " + message)
    
    now = datetime.now()
    encoded_string = message.encode("utf-8")

    time = now.strftime("%H:%M:%S")
    bucket_name = "my-lambda-sns-explore"
    file_key = "snsMessageObject"+time
    s3 = boto3.resource("s3")
    s3.Bucket(bucket_name).put_object(Key=file_key, Body=encoded_string)
    print("Written to S3 Bucket bucket_name" + bucket_name)
    return message
