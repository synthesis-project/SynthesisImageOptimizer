from requests.api import request
import pymongo
import urllib.request as url
import requests
import logging
import shutil
from conf import config
import uuid


logger = logging.getLogger('mongo')

def connect():
    conn_str = config['mongoUri']
    # set a 5-second connection timeout
    try:
        client = pymongo.MongoClient(conn_str, serverSelectionTimeoutMS=5000)
        return client
    except Exception:
        print("Unable to connect to the server.")
        return None


def get_database(database_name):
    client = connect()
    db = client[database_name]
    return db


def get_collection(db_name, collection_name):
    db = get_database(db_name)
    collection = db[collection_name]
    return collection


def get_collection_as_list(db_name, collection_name, filter):
    collection = get_collection(db_name, collection_name)
    return list(collection.find(filter))


def fetch_image(img_url, plant_slug):
    id = str(uuid.uuid4())
    local_file = f"/tmp/{plant_slug}-{id}.jpg"
    a = requests.get(img_url, stream=True)
    resp = {}
    resp['filename'] = local_file
    if a.status_code == 200:
        with open(local_file, 'wb') as f:
            resp['success'] = True
            shutil.copyfileobj(a.raw, f)
            logger.info(f'wrote file: {local_file}')
    return resp

"""
**kwargs -> {
    'db': str | database_name,
    'collection': str | collection,
    id: str | custom id
    o_id | ObjectId
}

"""
def delete_document(**kargs):
    col = get_collection(kargs['db'], kargs['collection'])
    if 'id' in kargs.keys():
        result =  col.delete_one({
            'id': kargs['id']
        })
        logger.info(f'deleted: {result.deleted_count}')
        return result
    elif 'o_id' in kargs.keys():
        result = col.delete_one({
            'o_id': kargs['id']
        })
        logger.info(f'deleted: {result.deleted_count}')
        return result
    return None
