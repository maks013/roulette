package com.casino.user.exception;

public class InvalidUserRegistration extends RuntimeException {
    public InvalidUserRegistration() {
        super("Can not register account");
    }
}
