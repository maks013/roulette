package com.casino.balance.dto;

import java.math.BigDecimal;

public record Withdraw(String id,
                       BigDecimal value) {
}
