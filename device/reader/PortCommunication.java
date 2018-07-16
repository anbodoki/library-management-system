package com.reader;

public interface PortCommunication {

    void connect() throws Exception;

    int readSymbol() throws Exception;

    int read(int length) throws Exception;

    void writeSymbol(int x) throws Exception;
}
