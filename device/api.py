from message_converter import generate_getBookInfo_message, parse_getBookInfo_response, generate_getClientInfo_message, \
    parse_getClientInfo_response
from tcp_client import send_message


def get_book_info(book_id):
    request_message = generate_getBookInfo_message(book_id)
    response = send_message(request_message)
    book_info = parse_getBookInfo_response(response)
    return book_info

def get_client_info(client_card_id):
    request_message = generate_getClientInfo_message(client_card_id)
    response = send_message(request_message)
    client_info = parse_getClientInfo_response(response)
    return client_info


def submit_book_request(user_id, book_id):
    pass


if __name__ == '__main__':
    book_info = get_book_info('111')
    client_info = get_client_info('222')