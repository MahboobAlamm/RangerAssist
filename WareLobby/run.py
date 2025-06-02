import os
from flask import Flask
from WareLobbyApplication.utils.logger import logger 
from WareLobbyApplication.db.mongoConnection import get_db
from WareLobbyApplication.controller.product_controller import product_bp 

app = Flask(__name__)
app.register_blueprint(product_bp, url_prefix='/lobby')

if __name__ == '__main__':
    host = os.getenv('FLASK_RUN_HOST', '0.0.0.0')
    port = int(os.getenv('FLASK_RUN_PORT', 5000))

    try:
        get_db()
    except Exception as e:
        logger.critical("Failed to connect to MongoDB. Exiting.")
        exit(1)
        
    logger.info("Starting WareLobby Flask Application....")
    app.run(host=host, port=port)




