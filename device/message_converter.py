from messages import GetBookInfoMessage, GetBookInfoResponse


def generate_getBookInfo_message(book_id):
    return bytes(GetBookInfoMessage(book_id))


def parse_getBookInfo_response(response):
    return GetBookInfoResponse(response).from_bytes(response)
