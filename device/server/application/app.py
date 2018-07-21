import sys

from application.api.book_submission.book_submission_api import BookSubmissions

sys.path.append('./..')

from application.api.client.client_api import ClientInfo
from application.api.book.book_api import BookInfo

from application.extensions import api
from application.flask_app import app


def init_extensions():
    api.init_app(app)


def init_resources():
    api.add_resource(BookInfo, '/books/<string:book_id>')
    api.add_resource(ClientInfo, '/clients/<string:client_id>')
    api.add_resource(BookSubmissions, '/booksubmissions')


def init_app():
    app.config.from_object('config.settings')
    init_resources()
    init_extensions()
    return app


if __name__ == '__main__':
    init_app().run(port=8080)