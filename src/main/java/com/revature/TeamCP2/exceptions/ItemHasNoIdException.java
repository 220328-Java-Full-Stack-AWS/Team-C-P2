package com.revature.TeamCP2.exceptions;

public class ItemHasNoIdException extends Exception {
    public ItemHasNoIdException() {
        super("The item has no Id");
    }

    public ItemHasNoIdException(String message) {
        super(message);
    }

    public ItemHasNoIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemHasNoIdException(Throwable cause) {
        super(cause);
    }
}
