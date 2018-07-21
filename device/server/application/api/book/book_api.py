from flask_restful import Resource

from lib.api import get_book_info


class BookInfo(Resource):

    def get(self, book_id):
        return get_book_info(book_id).msg_data
