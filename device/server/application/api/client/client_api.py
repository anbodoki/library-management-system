from flask_restful import Resource

from lib.api import get_card_info


class GetCardInfoResponse(object):

    card_id = None
    client_name = None

    def __init__(self, card_id, client_name):
        self.card_id = card_id
        self.client_name = client_name

    def serialize(self):
        return {
            "client_id": self.card_id,
            "client_name": self.client_name
        }


class CardInfo(Resource):

    def get(self, card_id):
        try:
            card_info = get_card_info(card_id)
        except Exception as e:
            return {"message": "Internal server error"}, 500
        if card_info.msg_status == 'e':
            return {"message": "Card " + str(card_id) + " not registered"}, 404
        return GetCardInfoResponse(card_id, card_info.msg_data).serialize(), 200
