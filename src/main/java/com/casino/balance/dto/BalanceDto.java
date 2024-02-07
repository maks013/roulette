package com.casino.balance.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BalanceDto(Long id,
                         BigDecimal value) {
}
