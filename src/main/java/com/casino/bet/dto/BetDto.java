package com.casino.bet.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record BetDto(
        Long id,
        BigDecimal amount,
        LocalDateTime timeOfPlacement,
        String betType,
        Long userId,
        Long gameId) {
}
