package com.casino.user.exception;

public class TakenEmail extends RuntimeException{
    public TakenEmail(){
        super("This email is already taken");
    }
}
