package com.casino.balance.dto;

import lombok.Builder;

public record TransferResult(String fromBalanceId,
                             String toBalanceId,
                             boolean transfered) {
    @Builder public TransferResult{}
}
