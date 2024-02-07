package com.casino.balance.exception;

public class BalanceNotFound extends RuntimeException{
    public BalanceNotFound(){
        super("Balance not found");
    }
}
