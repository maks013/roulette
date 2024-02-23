package com.casino.balance.domain;

import com.casino.balance.dto.BalanceDto;
import com.casino.balance.exception.InvalidBalanceCreation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "balances")
@NoArgsConstructor
@AllArgsConstructor
class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = BalanceValueConverter.class)
    private BalanceValue balanceValue;
    private Long userId;

    static Balance create(Long newBalanceId) {
        if (newBalanceId == null) {
            throw new InvalidBalanceCreation();
        }
        return new Balance(newBalanceId, BalanceValue.emptyForNewAccount(), newBalanceId);
    }

    Balance deposit(BigDecimal toDeposit) {
        return new Balance(id, balanceValue.deposit(toDeposit), userId);
    }

    Balance withdraw(BigDecimal toWithdraw) {
        return new Balance(id, balanceValue.withdraw(toWithdraw), userId);
    }

    BalanceDto toDto() {
        return BalanceDto.builder()
                .id(id)
                .value(this.balanceValue.getValueAsBigDecimal())
                .userId(userId)
                .build();
    }
}
