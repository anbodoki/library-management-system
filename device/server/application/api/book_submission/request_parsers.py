from datetime import datetime

from flask_restful import reqparse


def date_validator(d):
    datetime.strptime(d, '%Y-%m-%dT%H:%M:%S')
    return d


book_submission_request_body_parser = reqparse.RequestParser()
book_submission_request_body_parser.add_argument('date', type=date_validator, help='Invalid', required=True)
book_submission_request_body_parser.add_argument('client_id', type=str, help='Invalid', required=True)
book_submission_request_body_parser.add_argument('book_id', type=str, help='Invalid', required=True)
