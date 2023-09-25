package com.casino.balance.exception;

public class InvalidWithdrawException extends RuntimeException {
    public InvalidWithdrawException(){
        super("Withdraw can not be done");
    }
}
