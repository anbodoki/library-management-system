from flask_restful import Resource


class BookInfo(Resource):

    def get(self, book_id):
        return 'Book id: ' + str(book_id)

