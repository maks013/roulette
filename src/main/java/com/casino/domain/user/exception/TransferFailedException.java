package com.casino.domain.user.exception;

public class TransferFailedException extends RuntimeException {
    public TransferFailedException(){
        super("Transfer failed");
    }
}
