package com.casino.balance.exception;

public class InvalidTransferException extends RuntimeException {
    public InvalidTransferException(){
        super("Transfer can not be done");
    }
}
