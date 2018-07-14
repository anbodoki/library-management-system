package com.lms.gateway.model;

public class ErrorResponseData implements MessageData {

    private String data = "Happened unexpected error, please return book";

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
