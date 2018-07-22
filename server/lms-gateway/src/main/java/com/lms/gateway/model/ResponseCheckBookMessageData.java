package com.lms.gateway.model;

public class ResponseCheckBookMessageData implements MessageData {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String customToString() {
        return getData();
    }
}
