package com.lms.gateway.model;

public enum MessageStatus {

    OK('o'),
    IN_PROGRESS('i'),
    ERROR('e');

    private char value;

    MessageStatus(char i) {
        this.value = i;
    }

    public char getValue() {
        return value;
    }

    public static MessageStatus valueOf(char val) {
        for (MessageStatus packetType : MessageStatus.values()) {
            if (packetType.getValue() == val) {
                return packetType;
            }
        }
        return null;
    }
}
