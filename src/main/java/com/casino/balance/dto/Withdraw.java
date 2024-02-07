package com.casino.balance.dto;

import java.math.BigDecimal;

public record Withdraw(Long id,
                       BigDecimal value) {
}
