package com.lms.gateway.model;

public class RequestCheckBookMessageData implements MessageData {

    private String bookIdentifier;

    public String getBookIdentifier() {
        return bookIdentifier;
    }

    public void setBookIdentifier(String bookIdentifier) {
        this.bookIdentifier = bookIdentifier;
    }

    @Override
    public String customToString() {
        return getBookIdentifier();
    }
}
