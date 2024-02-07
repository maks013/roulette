package com.casino.balance.exception;

public class InvalidWithdraw extends RuntimeException {
    public InvalidWithdraw(){
        super("Withdraw can not be done");
    }
}
