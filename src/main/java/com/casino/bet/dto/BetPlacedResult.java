package com.casino.bet.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record BetPlacedResult(
        Long id,
        boolean placed,
        BigDecimal placedAmount,
        LocalDateTime timeOfPlacement,
        String betType,
        Long gameId
) {
}
