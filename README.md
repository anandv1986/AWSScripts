# AWSScripts
Amazon AWS Scripts

Scripts used in this repo is to learn AWS services like below use cases.
  1. Lambda_Get_Put_Objects.py -> Lambda to be invoked on the event of Put objects in S3 bucket, Lambda will put the same object in a different bucket.
  2. SnsToLambdaToS3.py -> Sns triggers Lambda whenever new message published to topic, Lambda triggers S3 after processing the message and uploads the message as a file to S3.
