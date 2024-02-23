package com.casino.user.dto;

import lombok.Builder;

@Builder
public record LoginResponseDto(
        String username,
        String token
) {
}
