package com.casino.domain.user.dto;

import lombok.Builder;

public record StatsResponseDto(
        int chipWorthFive,
        int chipWorthTwentyFive,
        int chipWorthHundred,
        int gamesPlayed,
        int gamesWon
) {
    @Builder public StatsResponseDto{}
}
