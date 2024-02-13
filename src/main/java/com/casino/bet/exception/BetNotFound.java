package com.casino.bet.exception;

public class BetNotFound extends RuntimeException {
    public BetNotFound() {
        super("Bet not found");
    }
}
