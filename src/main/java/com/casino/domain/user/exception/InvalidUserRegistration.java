package com.casino.domain.user.exception;

public class InvalidUserRegistration extends RuntimeException {
    public InvalidUserRegistration(){
        super("User can not be register");
    }
}
