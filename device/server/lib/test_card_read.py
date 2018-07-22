import io
import subprocess
from random import random
from time import sleep


def read_card(callback):
    sleep(5)
    callback(random())
    # reader = subprocess.Popen(['java', '-cp', '../lib/RXTXcomm.jar:../lib/reader.jar', 'com.reader.Main'], stdout=subprocess.PIPE)
    # lines = io.TextIOWrapper(reader.stdout, encoding='utf-8')
    # for line in lines:
    #     if line.startswith("#"):
    #         callback(line[1:])
