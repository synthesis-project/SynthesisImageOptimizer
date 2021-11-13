import boto3
from pprint import pprint
from conf import config
import logging

logger = logging.getLogger('s3')


def upload_image(local_image_path, s3_bucket, s3_key):
    client = boto3.client('s3')
    logger.info(f"attempting to upload s3://{s3_bucket}/{s3_key} from {local_image_path}")
    response = client.upload_file(
        local_image_path, 
        s3_bucket, 
        s3_key,
        ExtraArgs={'ACL': 'public-read'})
    http_url = f'https://{config["s3Bucket"]}.s3.us-east-2.amazonaws.com/{s3_key}'
    # return f's3://{s3_bucket}/{s3_key} from {local_image_path}'
    return http_url