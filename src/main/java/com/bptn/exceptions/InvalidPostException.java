package com.bptn.exceptions;

@SuppressWarnings("all")
public class InvalidPostException extends RuntimeException {

    public InvalidPostException() {
        super();
    }

    public InvalidPostException(String msg) {
        super(msg);
    }

    public InvalidPostException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidPostException(Throwable cause) {
        super(cause);
    }
}
