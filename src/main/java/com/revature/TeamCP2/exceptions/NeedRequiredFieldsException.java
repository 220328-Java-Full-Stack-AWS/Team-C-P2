package com.revature.TeamCP2.exceptions;

public class NeedRequiredFieldsException extends Exception {

    public NeedRequiredFieldsException() {
        super();
    }

    public NeedRequiredFieldsException(String message) {
        super(message);
    }

    public NeedRequiredFieldsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NeedRequiredFieldsException(Throwable cause) {
        super(cause);
    }
}
