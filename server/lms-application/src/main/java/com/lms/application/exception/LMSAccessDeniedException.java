package com.lms.application.exception;

public class LMSAccessDeniedException extends Exception {

    public LMSAccessDeniedException() {
        super();
    }

    public LMSAccessDeniedException(String message) {
        super(message);
    }

    public LMSAccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LMSAccessDeniedException(Throwable cause) {
        super(cause);
    }
}
