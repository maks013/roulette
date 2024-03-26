package com.casino.bet.domain;

import java.math.BigDecimal;

public enum BetType {
    RED(2), BLACK(2), GREEN(14);

    private final int times;

    BetType(int multiplier) {
        this.times = multiplier;
    }

    public BigDecimal calculateWinningAmount(BigDecimal amount){
        return amount.multiply(BigDecimal.valueOf(times));
    }
}
