import sys


sys.path.append('./..')

from application.api.card_reader.card_reader_api import CardRead
from application.api.book_submission.book_submission_api import BookSubmissions
from application.api.client.client_api import CardInfo
from application.api.book.book_api import BookInfo

from application.extensions import api, cors
from application.flask_app import app


def init_extensions():
    api.init_app(app)
    cors.init_app(app, resources={r"/api/*": {"origins": "*"}})


def init_resources():
    api.add_resource(BookInfo, '/api/books/<string:book_id>')
    api.add_resource(CardInfo, '/api/cards/<string:card_id>')
    api.add_resource(CardRead, '/api/cardread')
    api.add_resource(BookSubmissions, '/api/booksubmissions')


def init_app():
    app.config.from_object('config.settings')
    init_resources()
    init_extensions()
    return app


if __name__ == '__main__':
    init_app().run(port=8082)
