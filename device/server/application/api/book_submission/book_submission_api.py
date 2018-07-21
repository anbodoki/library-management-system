from flask_restful import Resource

from application.api.book_submission.request_parsers import run_api_request_body_parser


class BookSubmissions(Resource):

    def post(self):
        data = run_api_request_body_parser.parse_args()
        return str(data)