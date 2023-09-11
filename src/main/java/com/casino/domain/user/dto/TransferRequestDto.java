package com.casino.domain.user.dto;

public record TransferRequestDto(
        String id,
        int chipWorthFive,
        int chipWorthTwentyFive,
        int chipWorthHundred
) {
}
