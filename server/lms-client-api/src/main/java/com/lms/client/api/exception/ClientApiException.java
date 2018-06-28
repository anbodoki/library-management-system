package com.lms.client.api.exception;

public class ClientApiException extends Exception {

    public ClientApiException() {
        super();
    }

    public ClientApiException(String message) {
        super(message);
    }

    public ClientApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientApiException(Throwable cause) {
        super(cause);
    }
}
