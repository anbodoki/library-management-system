from datetime import datetime

from flask_restful import reqparse


def date_validator(d):
    if d:
        datetime.strptime(d, '%d/%m/%Y %H:%M:%S')
    return d


book_submission_request_body_parser = reqparse.RequestParser()
book_submission_request_body_parser.add_argument('date', type=date_validator, help='Invalid', required=False, nullable=True)
book_submission_request_body_parser.add_argument('card_id', type=str, help='Invalid', required=True)
book_submission_request_body_parser.add_argument('book_id', type=str, help='Invalid', required=True)
