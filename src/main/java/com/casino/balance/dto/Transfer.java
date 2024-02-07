package com.casino.balance.dto;

import java.math.BigDecimal;

public record Transfer(Long fromBalanceId,
                       Long toBalanceId,
                       BigDecimal value) {
}
