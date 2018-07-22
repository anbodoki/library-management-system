from flask_restful import Resource

from application.api.book_submission.request_parsers import book_submission_request_body_parser
from lib.api import submit_book_request


class BookSubmissions(Resource):

    def post(self):
        data = book_submission_request_body_parser.parse_args()
        book_request_response = submit_book_request(data['card_id'], data['book_id'], data['date'])
        return {"message": book_request_response.msg_data}
