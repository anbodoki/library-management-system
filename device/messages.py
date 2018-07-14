from abc import abstractmethod
from abc import ABC

class Message(ABC):

    @abstractmethod
    def __bytes__(self):
        pass

    @abstractmethod
    @classmethod
    def from_bytes(cls, data):
        pass






class GetBookInfoMessage(Message):


    def __init__(self, book_id):
        self.book_id = book_id

    def __bytes__(self):
        return b'get book request'

    @classmethod
    def from_bytes(cls, data):
        pass


class GetBookInfoResponse(Message):

    def __init__(self, response):
        self.response = response

    def __bytes__(self):
        return self.response

    def from_bytes(cls, data):
        pass




