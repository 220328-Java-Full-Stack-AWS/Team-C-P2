package com.revature.TeamCP2.exceptions;

public class UpdateFailedException extends Exception {

    public UpdateFailedException() {
        super("Could not update the item");
    }

    public UpdateFailedException(String message) {
        super(message);
    }

    public UpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateFailedException(Throwable cause) {
        super(cause);
    }
}
