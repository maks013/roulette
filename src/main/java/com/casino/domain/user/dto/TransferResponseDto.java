package com.casino.domain.user.dto;

import lombok.Builder;

public record TransferResponseDto(boolean transfered,
                                  String username) {
    @Builder public TransferResponseDto{}
}
