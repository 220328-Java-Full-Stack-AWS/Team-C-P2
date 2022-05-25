package com.revature.TeamCP2.exceptions;

public class NotAuthorizedException extends Exception {

    public NotAuthorizedException() {
        super("You are not authorized to make this request");
    }

    public NotAuthorizedException(String message) {
        super(message);
    }

    public NotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAuthorizedException(Throwable cause) {
        super(cause);
    }
}
