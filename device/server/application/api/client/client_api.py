from flask_restful import Resource

from lib.api import get_client_info


class ClientInfo(Resource):

    def get(self, client_id):
        return get_client_info(client_id).msg_data