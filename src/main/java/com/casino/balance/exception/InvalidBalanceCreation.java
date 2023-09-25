package com.casino.balance.exception;

public class InvalidBalanceCreation extends RuntimeException {
    public InvalidBalanceCreation() {
        super("Balance can not be created");
    }
}
