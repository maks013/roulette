package com.casino.balance.exception;

public class InvalidDepositException extends RuntimeException {
    public InvalidDepositException(){
        super("Deposit can not be lower than 0");
    }
}
