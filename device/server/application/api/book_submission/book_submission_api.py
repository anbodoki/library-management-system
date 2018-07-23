from flask_restful import Resource

from application.api.book_submission.request_parsers import book_submission_request_body_parser
from lib.api import submit_book_request


class BookSubmissions(Resource):

    def post(self):
        try:
            data = book_submission_request_body_parser.parse_args()
            book_submit_info = submit_book_request(data.card_id, data.book_id, data.date);
        except Exception as e:
            return {"message": "Internal server error"}, 500
        if book_submit_info.msg_status == 'e':
            return {"message": book_submit_info.msg_data}, 404

        # book_request_response = submit_book_request(data['card_id'], data['book_id'], data['date'])
        return {"message": book_submit_info.msg_data}
