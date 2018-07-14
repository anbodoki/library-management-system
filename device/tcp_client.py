import socket

TCP_IP = '127.0.0.1'
TCP_PORT = 5005
BUFFER_SIZE = 1024
MESSAGE = "Hello, World!"


def send_message(request_message):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((TCP_IP, TCP_PORT))
    s.send(request_message)
    data = s.recv(BUFFER_SIZE)
    s.close()
    # return '*b&o&date&book name by author&crc16'
    return data
