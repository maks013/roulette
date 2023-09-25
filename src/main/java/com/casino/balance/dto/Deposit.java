package com.casino.balance.dto;

import java.math.BigDecimal;

public record Deposit(String id,
                      BigDecimal value) {
}
