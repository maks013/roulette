package com.casino.user.dto;

import lombok.Builder;

@Builder
public record RegisterDto(String username,
                          String email,
                          String password) {
}
