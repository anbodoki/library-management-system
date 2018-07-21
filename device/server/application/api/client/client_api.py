from flask_restful import Resource


class ClientInfo(Resource):

    def get(self, client_id):
        return 'Client id: ' + str(client_id)