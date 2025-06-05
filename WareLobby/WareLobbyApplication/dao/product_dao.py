import os
from WareLobbyApplication.db.mongoConnection import get_db
from WareLobbyApplication.utils.logger import logger

db = get_db()
collection_name = os.getenv("MONGO_COLLECTION_NAME", "product")
product_collection = db[collection_name]

def insert_products(products):
    try:
        result = product_collection.insert_many(products)
        logger.info(f"Inserted {len(result.inserted_ids)} documents.")
        return result.inserted_ids
    except Exception as e:
        logger.error(f"DAO insert_products error: {e}")
        raise

def upsert_products(products):
    try:
        filter_column = {"product_name": products["product_name"]}
        update_data = {"$set": products}
        result = product_collection.update_one(filter_column, update_data, upsert=True)

        if result.upserted_id:
            logger.info(f"Inserted product: {products['product_name']}")
            return "inserted", result.upserted_id
        else:
            logger.info(f"Updated product: {products['product_name']}")
            return "updated", products["product_id"]
    except Exception as e:
        logger.error(f"Upsert failed: {e}")
        raise

def fetch_expire_proucts(current_utc):
    try:
        expired_prods = product_collection.find({
            "product_expiry_dt": {"$lt": current_utc}, 
        })

        expired_product_names = [prod.get("product_name") for prod in expired_prods]
        logger.info(f"Fetched {len(expired_product_names)} expired products.")
        return expired_product_names
    except Exception as e:
        logger.error(f"DAO fetch_expired_products error: {e}")
        raise