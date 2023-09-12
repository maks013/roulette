package com.casino.user.dto;

public record RegistrationResultDto(String id,
                                    boolean created,
                                    String username) {
}
