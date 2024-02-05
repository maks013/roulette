package com.casino.user.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(Long id,
                                    boolean created,
                                    String username) {
}
