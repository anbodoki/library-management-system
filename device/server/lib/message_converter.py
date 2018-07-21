from lib.messages import GetBookInfoMessage, GetBookInfoResponse, GetClientInfoMessage, GetClientInfoResponse, \
    GetSubmitMessage, GetSubmitResponse


def generate_getBookInfo_message(book_id):
    return bytes(GetBookInfoMessage(book_id))


def parse_getBookInfo_response(response):
    info_response = GetBookInfoResponse(response)
    info_response.from_bytes(response)
    return info_response

def generate_getClientInfo_message(client_card_id):
    return bytes(GetClientInfoMessage(client_card_id))


def parse_getClientInfo_response(response):
    info_response = GetClientInfoResponse(response)
    info_response.from_bytes(response)
    return info_response

def generate_submit_message(client_card_id, book_id, date):
    return bytes(GetSubmitMessage(client_card_id, book_id, date))

def parse_submit_response(response):
    info_response = GetSubmitResponse(response)
    info_response.from_bytes(response)
    return info_response