package com.casino.user.dto;

import lombok.Builder;

public record UserDto(
        String id,
        String username,
        String password) {
    @Builder
    public UserDto {
    }
}
