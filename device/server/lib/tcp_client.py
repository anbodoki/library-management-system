import socket

TCP_IP = '212.72.131.90'
TCP_PORT = 14002
BUFFER_SIZE = 1024
MESSAGE = "Hello, World!"


def send_message(request_message):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((TCP_IP, TCP_PORT))
    print('vagzavni mesijs: ', request_message)
    s.send(request_message)
    print('gavagzavne mesiji: ', request_message)
    data = s.recv(BUFFER_SIZE)
    print('mivige mesiji: ', data)
    s.close()
    return data
