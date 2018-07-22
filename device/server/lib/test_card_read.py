import io
import subprocess


def read_card(callback):
    reader = subprocess.Popen(['java', '-cp', 'RXTXcomm.jar:reader.jar', 'com.reader.Main'], stdout=subprocess.PIPE)
    lines = io.TextIOWrapper(reader.stdout, encoding='utf-8')
    for line in lines:
        if line.startswith("#"):
            callback(line[1:])
