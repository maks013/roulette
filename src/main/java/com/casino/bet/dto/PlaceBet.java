package com.casino.bet.dto;

import com.casino.bet.domain.BetType;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PlaceBet(
        Long userId,
        BigDecimal amount,
        BetType betType
) {
}
