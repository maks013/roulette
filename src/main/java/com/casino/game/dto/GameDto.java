package com.casino.game.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record GameDto(
        Long id,
        String winningBetType,
        LocalDateTime roundStart,
        LocalDateTime roundEnd
) {
}
