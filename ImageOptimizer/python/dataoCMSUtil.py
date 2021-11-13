from pprint import pprint
import requests, json

from requests.models import Response
from conf import config

img_fields = [
    'fertilization_image',
    'placement_image',
    'propogation_image',
    's3_image_url',
    's3_masonry_image_url',
    'water_image'
]


def get_content():
    headers = {
        'Accept': 'application/json',
        'Authorization': f'Bearer {config["dataOCmsApiKey"]}'
    }
    response = requests.get(headers=headers, url=config['dataOContentUrl'])
    j = json.loads(response.content)
    data = j['data']
    return data


def get_missing_image_field(item):
    missing_fields = []
    if item['attributes']['fertilization_image'] == '' or item['attributes']['fertilization_image'] == 'tbd':
        missing_fields.append('fertilization_image')
    if item['attributes']['placement_image'] == '' or item['attributes']['placement_image'] == 'tbd':
        missing_fields.append('placement_image')
    if item['attributes']['propogation_image'] == '' or item['attributes']['propogation_image'] == 'tbd':
        missing_fields.append('propogation_image')
    if item['attributes']['s3_image_url'] == '' or item['attributes']['s3_image_url'] == 'tbd':
        missing_fields.append('s3_image_url')
    if item['attributes']['s3_masonry_image_url'] == '' or item['attributes']['s3_masonry_image_url'] == 'tbd':
        missing_fields.append('s3_masonry_image_url')
    if item['attributes']['water_image'] == '' or item['attributes']['water_image'] == 'tbd':
        missing_fields.append('water_image')
    return missing_fields


def get_image_slug(plant_name):
    content = get_content()
    for _ in content:
        if _['attributes']['plant_name'].lower() == plant_name.lower():
            return _['attributes']['slug']
    return None


def update_content_by_id(**args):
    id = args['id']
    url = f'https://site-api.datocms.com/items/{id}'
    attributes = args['attributes']
    data = {}
    data['id'] = id
    data['attributes'] = attributes
    data['type'] = 'item'
    headers = {
        'Accept': 'application/json',
        'Authorization': f'Bearer {config["dataOCmsApiKey"]}',
        'Content-Type': 'application/json'
    }
    d = {}
    d['data'] = data
    response = requests.put(url, data=json.dumps(d), headers=headers) 
    print(response.status_code, response.content)
    return response


def get_content_by_name(plant_name):
    all_content = get_content()
    for _ in all_content:
        name = _['attributes']['plant_name']
        if name.lower() == plant_name.lower():
            return _
    return None