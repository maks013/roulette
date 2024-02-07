package com.casino.balance.domain;

import com.casino.balance.exception.InvalidDeposit;
import com.casino.balance.exception.InvalidWithdraw;

import java.math.BigDecimal;
import java.math.RoundingMode;

record BalanceValue(BigDecimal value) implements Comparable<BalanceValue> {

    static BalanceValue emptyForNewAccount() {
        return new BalanceValue(BigDecimal.ZERO);
    }

    BalanceValue deposit(BigDecimal depositValue) {
        if (depositValue == null || BigDecimal.ZERO.compareTo(depositValue) >= 0) {
            throw new InvalidDeposit();
        }
        return new BalanceValue(this.value.add(depositValue));
    }

    BalanceValue withdraw(BigDecimal withdrawValue) {
        if (withdrawValue == null || withdrawValue.compareTo(this.value) > 0) {
            throw new InvalidWithdraw();
        }
        return new BalanceValue(this.value.subtract(withdrawValue));
    }

    BigDecimal getValueAsBigDecimal() {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public int compareTo(BalanceValue balanceValue) {
        return getValueAsBigDecimal().compareTo(balanceValue.getValueAsBigDecimal());
    }
}
