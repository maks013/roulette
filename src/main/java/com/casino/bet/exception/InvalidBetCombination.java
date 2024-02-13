package com.casino.bet.exception;

public class InvalidBetCombination extends RuntimeException {
    public InvalidBetCombination(){
        super("Incorrect combination of placed bet types");
    }
}
