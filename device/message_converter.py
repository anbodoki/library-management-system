from messages import GetBookInfoMessage, GetBookInfoResponse, GetClientInfoMessage, GetClientInfoResponse


def generate_getBookInfo_message(book_id):
    return bytes(GetBookInfoMessage(book_id))


def parse_getBookInfo_response(response):
    return GetBookInfoResponse(response).from_bytes(response)

def generate_getClientInfo_message(client_card_id):
    return bytes(GetClientInfoMessage(client_card_id))


def parse_getClientInfo_response(response):
    return GetClientInfoResponse(response).from_bytes(response)