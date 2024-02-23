package com.casino.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequestDto(
        @NotBlank(message = "username can not be blank")
        String username,
        @NotBlank(message = "password can not be blank")
        String password
) {
}
