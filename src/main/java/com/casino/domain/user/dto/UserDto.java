package com.casino.domain.user.dto;

import lombok.Builder;

public record UserDto(
        String id,
        String username,
        String password,
        int chipWorthFive,
        int chipWorthTwentyFive,
        int chipWorthHundred,
        int gamesPlayed,
        int gamesWon
) {

    @Builder public UserDto{}
}
