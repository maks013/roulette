package com.casino.balance.dto;

import lombok.Builder;

import java.math.BigDecimal;

public record BalanceDto(String id,
                         BigDecimal value) {
    @Builder public BalanceDto{}
}
