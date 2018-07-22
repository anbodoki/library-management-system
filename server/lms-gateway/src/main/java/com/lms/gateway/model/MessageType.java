package com.lms.gateway.model;

public enum MessageType {

    CHECK_BOOK('b'),
    CHECK_CLIENT('c'),
    SUBMIT('s');

    private char value;

    MessageType(char c) {
        this.value = c;
    }

    public char getValue() {
        return value;
    }

    public static MessageType valueOf(char val) {
        for (MessageType packetType : MessageType.values()) {
            if (packetType.getValue() == val) {
                return packetType;
            }
        }
        return null;
    }
}