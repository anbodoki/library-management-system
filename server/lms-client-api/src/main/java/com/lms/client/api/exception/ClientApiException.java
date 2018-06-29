package com.lms.client.api.exception;

import com.lms.client.api.messages.Messages;

public class ClientApiException extends Exception {

    private ClientStatusCode code;

    public ClientApiException() {
        super();
    }

    public ClientApiException(ClientStatusCode code) {
        super(Messages.get(code.name()));
        this.code = code;
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

    public ClientStatusCode getCode() {
        return code;
    }

    public void setCode(ClientStatusCode code) {
        this.code = code;
    }
}
