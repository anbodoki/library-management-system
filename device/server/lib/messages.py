from datetime import datetime
from abc import ABC
from enum import Enum


class MessageType():
    CHECK_BOOK = 'b'
    CHECK_CLIENT = 'c'
    SUBMIT = 's'


class MessageStatus():
    OK = 'o'
    ERROR = 'e'
    IN_PROGRESS = 'i'


SYNC_BYTE = '*'


class InvalidMessageException(Exception):
    pass


class Message(ABC):

    msg_type = None
    msg_status = MessageStatus.IN_PROGRESS
    date = None
    msg_data = None
    crc16 = '12345'

    def __init__(self, msg_data):
        self.date = datetime.now()
        self.msg_data = msg_data

    def __bytes__(self):
        seq = (self.msg_type, self.msg_status, self.date.strftime('%m/%d/%Y %X'), self.msg_data, self.crc16)
        return (SYNC_BYTE + '&'.join(seq)).encode()

    def from_bytes(self, data):
        data = data.decode()
        try:
            self.msg_type, self.msg_status, self.date, self.msg_data, self.crc16 = data[1:].split('&')
        except Exception as e:
            raise InvalidMessageException("Invalid message")
        self.validate_message(data)

    def validate_message(self, message):
        self.validate_sync_byte(message[0])

    def validate_sync_byte(self, sync_byte):
        if sync_byte != SYNC_BYTE:
            raise InvalidMessageException("Invalid sync byte")


class GetBookInfoMessage(Message):

    def __init__(self, book_id):
        super().__init__(book_id)
        self.book_id = book_id
        self.msg_type = MessageType.CHECK_BOOK


class GetBookInfoResponse(Message):

    def __init__(self, response):
        super().__init__(response)
        self.response = response

    def from_bytes(self, data):
        super().from_bytes(data)
        self.validate_msg_type()
        if self.msg_status != 'e':
            self.msg_data, self.action = self.msg_data.split('@')

    def validate_msg_type(self):
        if self.msg_type != MessageType.CHECK_BOOK:
            raise InvalidMessageException("Invalid message type")


class GetClientInfoMessage(Message):

    def __init__(self, client_card_id):
        super().__init__(client_card_id)
        self.client_card_id = client_card_id
        self.msg_type = MessageType.CHECK_CLIENT


class GetClientInfoResponse(Message):

    def __init__(self, client_info):
        super().__init__(client_info)
        self.client_info = client_info

    def from_bytes(self, data):
        super().from_bytes(data)
        self.validate_msg_type()

    def validate_msg_type(self):
        if self.msg_type != MessageType.CHECK_CLIENT:
            raise InvalidMessageException("Invalid message type")


class GetSubmitMessage(Message):
    def __init__(self, client_card_id, book_id, date):
        if not date:
            date = "null"
        message_data = ('@').join((book_id, client_card_id, date))
        super().__init__(message_data)
        self.msg_type = MessageType.SUBMIT


class GetSubmitResponse(Message):

    def __init__(self, response):
        super().__init__(response)
        self.response = response

    def from_bytes(self, data):
        super().from_bytes(data)
        self.validate_msg_type()

    def validate_msg_type(self):
        if self.msg_type != MessageType.SUBMIT:
            raise InvalidMessageException("Invalid message type")