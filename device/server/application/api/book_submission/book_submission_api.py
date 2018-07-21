from flask_restful import Resource

from application.api.book_submission.request_parsers import run_api_request_body_parser
from application.api.book_submission.request_parsers import book_submission_request_body_parser
from lib.api import submit_book_request


class BookSubmissions(Resource):

    def post(self):
        data = book_submission_request_body_parser.parse_args()
        return submit_book_request(data['client_id'], data['book_id'], data['date']).msg_data