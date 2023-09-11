package com.casino.domain.user.dto;

import lombok.Builder;

public record DepositResponseDto(boolean deposited) {
    @Builder
    public DepositResponseDto {
    }
}
