import socket

TCP_IP = '212.72.131.90'
TCP_PORT = 14002
BUFFER_SIZE = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)


# conn, addr = s.accept()
# print('Connection address:', addr)

while True:
    conn, addr = s.accept()
    print('Connection address:', addr)
    data = conn.recv(BUFFER_SIZE)
    if not data:
        break
    print("received data:", data)

    data = data.decode()

    msg_type, msg_status, date, msg_data, crc16 = data[1:].split('&')

    if msg_type == 'b':
        conn.send(b'*b&o&date&book name by author&crc16')
        print("send book data:", b'*b&o&date&book name by author&crc16')

    if msg_type == 'c':
        conn.send(b'*c&o&date&client name&crc16')
        print("send client data:", b'*c&o&date&client name&crc16')

    if msg_type == 's':
        conn.send(b'*s&o&date&successfully taken&crc16')
        print("send submit data:", b'*c&o&date&client name&crc16')
    # conn.close()