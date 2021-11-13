from conf import config
import dataoCMSUtil as cms
import mongoUtil as mongo
from pprint import pp, pprint
import s3Util as s3
import uuid
import sys
import logging
import argparse
parser = argparse.ArgumentParser()
parser.add_argument("-i", "--input", help="Perform image processing for single plant or list of plants")


logger = logging.getLogger('main')


def process_content(plants):
    # data = cms.get_content()
    responses = {}
    for plant in plants:
        plant_data = cms.get_content_by_name(plant)
        cms_id = plant_data['id']
        slug = plant_data['attributes']['slug']
        name = plant_data['attributes']['plant_name']
        col = mongo.get_collection_as_list('raw', 'accepted_images', {'plantName': name.lower()})
        num_images = len(col)
        if num_images == 0:
            logger.info(f'No accepted images for {name}...skipping')
            print(f'No accepted images for {name}')
            continue
        attr = {}
        missing_image_fields = cms.get_missing_image_field(plant_data)
        print(f'Running for {plant}...missing {missing_image_fields}')
        for image_type in missing_image_fields:
            if len(col) > 0:
                img = col.pop()
                fetch_result = mongo.fetch_image(img['url'], slug)
                if fetch_result['success']:
                    id = str(uuid.uuid4())
                    s3_key = f'{slug}_{id}.jpg'
                    img_def = {
                        'local_image_path': fetch_result['filename'],
                        's3_bucket': config['s3Bucket'],
                        's3_key': f'{image_type}/{s3_key}'
                    }
                    s3_url = s3.upload_image(**img_def)
                    attr[image_type] = s3_url
                    d_arg = {
                        'db': 'raw',
                        'collection': 'accepted_images',
                        'id': img['id']
                    }
                    mongo.delete_document(**d_arg)
        update_args = {
            'id': cms_id,
            'attributes': attr
        }
        r = cms.update_content_by_id(update_args)
        responses[plant] = r
    return responses
        


if __name__ == "__main__":
    args = parser.parse_args()
    i = args.input.split(',')
    r = process_content(i)
    pprint(r)
