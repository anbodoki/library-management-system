package com.lms.gateway.model;

public class RequestCheckClientMessageData implements MessageData {

    private String clientCardId;

    public String getClientCardId() {
        return clientCardId;
    }

    public void setClientCardId(String clientCardId) {
        this.clientCardId = clientCardId;
    }

    @Override
    public String customToString() {
        return getClientCardId();
    }
}
