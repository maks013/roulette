package com.casino.bet.dto;

import com.casino.bet.domain.BetType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PlaceBet(
        Long userId,
        BigDecimal amount,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        BetType betType
) {
}
