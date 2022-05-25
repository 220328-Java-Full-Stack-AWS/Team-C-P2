package com.revature.TeamCP2.exceptions;

public class ItemHasNonNullIdException extends Exception {

    public ItemHasNonNullIdException() {
        super("The item already has an id");
    }

    public ItemHasNonNullIdException(String message) {
        super(message);
    }

    public ItemHasNonNullIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemHasNonNullIdException(Throwable cause) {
        super(cause);
    }
}
