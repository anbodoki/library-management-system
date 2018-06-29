package com.lms.client.api.exception;

public enum ClientStatusCode {

    INTERNAL_SERVER_ERROR(10);

    private int value;

    ClientStatusCode(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
