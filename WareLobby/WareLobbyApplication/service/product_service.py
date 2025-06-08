from WareLobbyApplication.model.product import Product
from WareLobbyApplication.dao.product_dao import insert_products, upsert_products, fetch_expire_proucts, delete_expired_product_from_db
from WareLobbyApplication.utils.logger import logger
from datetime import datetime, timezone

def add_product(product_list):
    try:
        products_to_insert = []

        for item in product_list:
            product = Product(
                product_name=item.get("product_name"),
                product_qty=item.get("product_qty"),
                product_category=item.get("product_category"),
                product_brand=item.get("product_brand"),
                product_expiry_dt=datetime.fromisoformat(item.get("product_expiry_dt").replace("Z", "+00:00")),
                product_price=item.get("product_price"),
                product_fill_dt=item.get("product_fill_dt")
            )
            products_to_insert.append(product.to_dict())

        inserted_ids = insert_products(products_to_insert)

        return {
            "status": "success",
            "inserted_count": len(inserted_ids),
            "product_ids": [p["product_id"] for p in products_to_insert]
        }

    except Exception as e:
        logger.error(f"Service add_product error: {e}")
        return {"status": "error", "message": str(e)}


def upsert_product(product_list):
    results = {
        "inserted": [],
        "updated": [],
        "failed": []
    }

    for item in product_list:
        try:
            product = Product(
                product_name=item.get("product_name"),
                product_qty=item.get("product_qty"),
                product_category=item.get("product_category"),
                product_brand=item.get("product_brand"),
                product_expiry_dt=datetime.fromisoformat(item.get("product_expiry_dt").replace("Z", "+00:00")),
                product_price=item.get("product_price"),
                product_fill_dt=item.get("product_fill_dt")
            )
            status, product_id = upsert_products(product.to_dict())
            results[status].append({
                "product_name" : product.product_name,
                "product_id" : str(product_id)
            })
        except Exception as e:
            logger.error(f"Error processing product '{item.get('product_name')}': {e}")
            results["failed"].append({
                "product_name": item.get("product_name"),
                "error": str(e)
            })

    return {"status": "success", "result": results}

def get_expired_products():
    current_utc = datetime.now(timezone.utc)
    try:
        expired_products = fetch_expire_proucts(current_utc)
        return {
            "status": "success",
            "expired_product": expired_products
        }
    except Exception as e:
        logger.error(f"Service get_expired_product_names error: {e}")
        return {         
            "status": "error",
            "message": str(e)
        }
    
def delete_expired_product():
    current_utc = datetime.now(timezone.utc)
    try:
        deleted = delete_expired_product_from_db(current_utc)
        return {
            "status": "success",
            "deleted_count": len(deleted),
            "deleted_products": deleted
        }
    except Exception as e:
        logger.error(f"Service delete_expired_products error: {e}")
        return {
            "status:": "error",
            "message": str(e)
        }