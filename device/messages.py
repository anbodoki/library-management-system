from datetime import datetime
from abc import ABC
from enum import Enum


SYNC_BYTE = '*'

class InvalidMessageException(Exception):
    pass


class Message(ABC):

    msg_type = None
    msg_status = 'i'
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
            self.validate_message(data)
        except Exception as e:
            raise InvalidMessageException("Invalid message")

    def validate_message(self, message):
        self.validate_sync_byte(message[0])
        self.validate_msg_status()

    def validate_sync_byte(self, sync_byte):
        if sync_byte != SYNC_BYTE:
            raise InvalidMessageException("Invalid sync byte")

    def validate_msg_status(self):
        if self.msg_status == 'e':
            raise InvalidMessageException("Server error: " + self.msg_data)


class GetBookInfoMessage(Message):

    def __init__(self, book_id):
        super().__init__(book_id)
        self.book_id = book_id
        self.msg_type = 'b'


class GetBookInfoResponse(Message):

    def __init__(self, response):
        super().__init__(response)
        self.response = response

    def from_bytes(self, data):
        print(data)
        super().from_bytes(data)
        self.validate_msg_type()
        print(self.msg_data, self.date)

    def validate_msg_type(self):
        if self.msg_type == 'b':
            raise InvalidMessageException("Invalid message type")


