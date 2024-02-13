package com.casino.balance.domain;

import com.casino.balance.dto.*;
import com.casino.balance.exception.BalanceNotFound;
import com.casino.balance.exception.InvalidBalanceCreation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BalanceFacade {

    private final BalanceRepository balanceRepository;

    public BalanceDto createBalance(Long newBalanceId) {
        if (balanceRepository.findById(newBalanceId).isPresent()) {
            throw new InvalidBalanceCreation();
        }
        final Balance balance = Balance.create(newBalanceId);

        return balanceRepository.save(balance).toDto();
    }

    public BalanceDto deposit(Deposit deposit) {
        final Balance balance = getBalance(deposit.id());
        return balanceRepository.save(balance.deposit(deposit.value())).toDto();
    }

    public BalanceDto withdraw(Withdraw withdraw) {
        final Balance balance = getBalance(withdraw.id());
        return balanceRepository.save(balance.withdraw(withdraw.value())).toDto();
    }

    public TransferResult transfer(Transfer transfer) {
        Balance balanceFromResult = getBalance(transfer.fromBalanceId()).withdraw(transfer.value());
        Balance balanceToResult = getBalance(transfer.toBalanceId()).deposit(transfer.value());

        balanceRepository.save(balanceFromResult);
        balanceRepository.save(balanceToResult);

        return TransferResult.builder()
                .created(true)
                .fromBalanceId(transfer.fromBalanceId())
                .toBalanceId(transfer.toBalanceId())
                .build();
    }

    public BalanceDto findBalanceById(Long id) {
        final Balance balance = getBalance(id);

        return BalanceDto.builder()
                .id(balance.getId())
                .value(balance.getBalanceValue().getValueAsBigDecimal())
                .build();
    }

    private Balance getBalance(Long id) {
        return balanceRepository.findById(id).orElseThrow(BalanceNotFound::new);
    }
}
