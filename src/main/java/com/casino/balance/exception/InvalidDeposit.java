package com.casino.balance.exception;

public class InvalidDeposit extends RuntimeException {
    public InvalidDeposit(){
        super("Deposit can not be lower than 0");
    }
}
