package com.casino.user.exception;

public class InvalidEmailFormat extends RuntimeException {
    public InvalidEmailFormat() {
        super("Incorrect format of email");
    }
}
