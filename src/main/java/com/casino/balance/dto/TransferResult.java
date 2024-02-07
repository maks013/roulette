package com.casino.balance.dto;

import lombok.Builder;

@Builder
public record TransferResult(Long fromBalanceId,
                             Long toBalanceId,
                             boolean created) {
}
