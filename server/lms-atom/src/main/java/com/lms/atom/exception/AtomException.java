package com.lms.atom.exception;

public class AtomException extends Exception {

    public AtomException() {
        super();
    }

    public AtomException(String message) {
        super(message);
    }

    public AtomException(String message, Throwable cause) {
        super(message, cause);
    }

    public AtomException(Throwable cause) {
        super(cause);
    }
}
