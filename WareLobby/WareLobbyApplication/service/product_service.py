from WareLobbyApplication.model.product import Product
from WareLobbyApplication.dao.product_dao import insert_products, upsert_products
from WareLobbyApplication.utils.logger import logger

def add_product(product_list):
    try:
        products_to_insert = []

        for item in product_list:
            product = Product(
                product_name=item.get("product_name"),
                product_qty=item.get("product_qty"),
                product_category=item.get("product_category"),
                product_brand=item.get("product_brand"),
                product_expiry_dt=item.get("product_expiry_dt"),
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
                product_expiry_dt=item.get("product_expiry_dt"),
                product_price=item.get("product_price"),
                product_fill_dt=item.get("product_fill_dt")
            )
            status, product_id = upsert_products(product.to_dict())
            results[status].append({
                "product_name" : product.product_name,
                "product_id" : product_id
            })
        except Exception as e:
            logger.error(f"Error processing product '{item.get('product_name')}': {e}")
            results["failed"].append({
                "product_name": item.get("product_name"),
                "error": str(e)
            })

    return {"status": "success", "result": results}