from threading import Thread

from flask_restful import Resource
from lib.test_card_read import read_card

card_id = None


def set_card_id(id):
    global card_id
    card_id = id


class CardRead(Resource):

    def post(self):
        global card_id
        card_id = None
        Thread(target=lambda: read_card(set_card_id)).start()
        return {"message": "Card read slot cleared successfully"}, 200

    def get(self):
        global card_id
        if card_id is None:
            return {"message": "Card id not found"}, 404
        return {"card_id": card_id}, 200
