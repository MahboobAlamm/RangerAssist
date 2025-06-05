from flask import Blueprint, request, jsonify
from WareLobbyApplication.utils.logger import logger
from WareLobbyApplication.service.product_service import add_product, upsert_product, get_expired_products

product_bp = Blueprint('product', __name__)

@product_bp.route('/', methods=['GET'])
def home():
    return "WarehouseLobbyApplication STARTED...."

@product_bp.route('/fillProducts', methods=['POST'])
def fill_products():
    product_data = request.get_json()
    logger.info("Received data from url, /lobby/fillProducts: ", product_data)
    if not product_data:
        return jsonify({"error": "No data provided"}), 400

    result = add_product(product_data)
    if result["status"] == "success":
        return jsonify(result), 200
    else:
        return jsonify(result), 500

@product_bp.route('/updateFillProducts', methods=['POST'])
def update_fill_produts():
    product_data = request.get_json()
    logger.info("Received data from url, /lobby/fillProducts: ", product_data)
    if not product_data:
        return jsonify({"error": "No data provided"}), 400
    
    result = upsert_product(product_data)
    return jsonify(result), 200

@product_bp.route('/getExpiredProducts', methods=['GET'])
def get_expiry_products():
    result = get_expired_products()
    if(result['status'] == 'success'):
        return jsonify(result), 200
    else:
        return jsonify(result), 500