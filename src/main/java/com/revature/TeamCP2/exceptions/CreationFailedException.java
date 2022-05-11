package com.revature.TeamCP2.exceptions;

public class CreationFailedException extends Exception {

    public CreationFailedException() {
        super("Could not create the item");
    }

    public CreationFailedException(String message) {
        super(message);
    }

    public CreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreationFailedException(Throwable cause) {
        super(cause);
    }
}
