from flask_restful import Resource

from lib.api import get_book_info


class GetBookResponse(object):

    def __init__(self, book_info):
        self.message = book_info.msg_data
        self.status = 'returned' if book_info.action == 'r' else 'borrowed'

    def serialize(self):
        return {
            'message': self.message,
            'status': self.status
        }


class BookInfo(Resource):

    def get(self, book_id):
        try:
            book_info = get_book_info(book_id)
        except Exception as e:
            return {"message": "Internal server error"}, 500
        if book_info.msg_status == 'e':
            return {"message": "Book " + str(book_id) + " not found"}, 404
        response = GetBookResponse(book_info).serialize()
        return response, 200
