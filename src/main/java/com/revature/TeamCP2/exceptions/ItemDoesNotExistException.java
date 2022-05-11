package com.revature.TeamCP2.exceptions;

public class ItemDoesNotExistException extends Exception {

    public ItemDoesNotExistException() {
        super("This item does not exist");
    }

    public ItemDoesNotExistException(String message) {
        super(message);
    }

    public ItemDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
