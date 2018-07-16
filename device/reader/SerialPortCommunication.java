package com.reader;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;


public class SerialPortCommunication implements PortCommunication {

    private InputStream reader;
    private String portName;
    private OutputStream writer;

    public SerialPortCommunication(String portName) {
        super();
        this.portName = portName;
    }

    public void connect() throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                this.writer = serialPort.getOutputStream();
                this.reader = serialPort.getInputStream();
            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public synchronized int readSymbol() throws Exception {
        return this.reader.read();
    }

    public synchronized int read(int length) throws Exception {
        return this.reader.read();
    }

    public synchronized void writeSymbol(int x) throws Exception {
        this.writer.write(x);
    }
}