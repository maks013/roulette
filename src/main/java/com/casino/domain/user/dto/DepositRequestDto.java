package com.casino.domain.user.dto;

public record DepositRequestDto(
        int chipWorthFive,
        int chipWorthTwentyFive,
        int chipWorthHundred
) {
}
