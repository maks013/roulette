package com.casino.user.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id,
                      String username,
                      String email,
                      String password,
                      String role) {
}
