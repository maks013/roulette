package com.casino.balance.domain;

import com.casino.balance.dto.BalanceDto;
import com.casino.balance.exception.InvalidBalanceCreation;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
class Balance{

    String id;
    BalanceValue balanceValue;

    static Balance create(String balanceId) {
        if (balanceId == null) {
            throw new InvalidBalanceCreation();
        } else {
            return new Balance(balanceId, BalanceValue.emptyForNewAccount());
        }
    }

    Balance deposit(BigDecimal toDeposit) {
        return new Balance(id, balanceValue.deposit(toDeposit));
    }

    Balance withdraw(BigDecimal toWithdraw) {
        return new Balance(id, balanceValue.withdraw(toWithdraw));
    }

    BalanceDto toDto() {
        return BalanceDto.builder()
                .id(id)
                .value(this.balanceValue.getValueAsBigDecimal())
                .build();
    }
}
