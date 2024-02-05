package com.casino.user.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        super("User not found");
    }
}
