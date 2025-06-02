import uuid
from datetime import datetime, timezone

class Product:
    def __init__(self, product_name=None, product_qty=0, product_category=None,
                 product_brand=None, product_expiry_dt=None, product_price=0.0,
                 product_fill_dt=None):
        self.product_name = product_name
        self.product_qty = product_qty
        self.product_category = product_category
        self.product_brand = product_brand
        self.product_expiry_dt = product_expiry_dt
        self.product_price = product_price
        self.product_fill_dt = product_fill_dt

    def __init__(self, product_name, product_qty, product_category, product_brand, 
                 product_expiry_dt, product_price, product_fill_dt=None):
        self.product_id = str(uuid.uuid4())
        self.product_name = product_name
        self.product_qty = product_qty
        self.product_category = product_category
        self.product_brand = product_brand
        self.product_expiry_dt = product_expiry_dt  
        self.product_price = product_price
        self.product_fill_dt = product_fill_dt or datetime.now(timezone.utc)

    def to_dict(self):
        return {
            "product_id": self.product_id,
            "product_name": self.product_name,
            "product_qty": self.product_qty,
            "product_category": self.product_category,
            "product_brand": self.product_brand,
            "product_expiry_dt": self.product_expiry_dt,
            "product_fill_dt": self.product_fill_dt,
            "product_price": self.product_price
        }