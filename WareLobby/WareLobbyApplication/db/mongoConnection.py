import os
from pymongo import MongoClient, errors
from WareLobbyApplication.utils.logger import logger

def get_db():
    try:
        mongo_uri = os.getenv("MONGO_URI", "mongodb://localhost:27017/")
        db_name = os.getenv("MONGO_DB_NAME", "warehouse")
        collection_name = os.getenv("MONGO_COLLECTION_NAME", "product")

        client = MongoClient(mongo_uri)
        logger.info("MongoDB connection established.")
    except errors.ConnectionFailure as e:
        logger.error(f"MongoDB connection failed: {e}")
        raise

    try:
        db = client[db_name]
        logger.info("Database 'warehouse' selected.")
    except Exception as e:
        logger.error(f"Failed to select database: {e}")
        raise

    try:
        if collection_name not in db.list_collection_names():
            db.create_collection(collection_name)
            db[collection_name].create_index("product_name", unique=True)
            logger.info("Collection 'product' created with unique index on 'product_name'.")
        else:
            logger.debug("Collection 'product' already exists.")
    except errors.CollectionInvalid as e:
        logger.error(f"Collection creation error: {e}")
        raise
    except errors.OperationFailure as e:
        logger.error(f"Index creation error or permission issue: {e}")
        raise
    except Exception as e:
        logger.error(f"Unexpected error during collection/index setup: {e}")
        raise

    return db
