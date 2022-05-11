package com.revature.TeamCP2.exceptions;

public class DeletionFailedException extends Exception {

    public DeletionFailedException() {
        super("Could not delete the item");
    }

    public DeletionFailedException(String message) {
        super(message);
    }

    public DeletionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeletionFailedException(Throwable cause) {
        super(cause);
    }
}
