package com.casino.balance.dto;

import java.math.BigDecimal;

public record Deposit(Long id,
                      BigDecimal value) {
}
