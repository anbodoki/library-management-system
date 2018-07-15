from message_converter import generate_getBookInfo_message, parse_getBookInfo_response

from tcp_client import send_message


def get_book_info(book_id):
    request_message = generate_getBookInfo_message(book_id)
    response = send_message(request_message)
    book_info = parse_getBookInfo_response(response)
    return book_info


def get_user_info(user_id):
    pass


def submit_book_request(user_id, book_id):
    pass


if __name__ == '__main__':
    book_info = get_book_info('111')