package com.bptn.exceptions;

public class InvalidUserNameException extends Exception {

    public InvalidUserNameException() {
        super();
    }

    public InvalidUserNameException(String msg) {
        super(msg);
    }

    public InvalidUserNameException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidUserNameException(Throwable cause) {
        super(cause);
    }
}
