package com.casino.domain.user.dto;

public record RegistrationResultDto(String id,
                                    boolean created,
                                    String username) {
}
