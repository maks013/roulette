package com.casino.balance.domain;

import com.casino.balance.dto.*;
import com.casino.balance.exception.BalanceNotFound;
import com.casino.balance.exception.InvalidBalanceCreation;
import com.casino.balance.exception.InvalidTransferException;
import com.casino.balance.exception.InvalidWithdrawException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BalanceFacade {

    private final BalanceRepository balanceRepository;

    public BalanceDto createBalance(String id) {
        if (balanceRepository.findById(id).isPresent()) {
            throw new InvalidBalanceCreation();
        }
        final Balance balance = Balance.create(id);

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
        final Balance balanceFrom = getBalance(transfer.fromBalanceId());
        final Balance balanceTo = getBalance(transfer.toBalanceId());

        try {
            Balance balanceFromResult = balanceFrom.withdraw(transfer.value());
            Balance balanceToResult = balanceTo.deposit(transfer.value());

            balanceRepository.save(balanceFromResult);
            balanceRepository.save(balanceToResult);

            return TransferResult.builder()
                    .transfered(true)
                    .fromBalanceId(transfer.fromBalanceId())
                    .toBalanceId(transfer.toBalanceId())
                    .build();
        } catch (RuntimeException exception) {
            throw new InvalidTransferException();
        }
    }

    public BalanceDto getBalanceById(String id) {
        final Balance balance = getBalance(id);

        return BalanceDto.builder()
                .id(balance.getId())
                .value(balance.getBalanceValue().getValueAsBigDecimal())
                .build();
    }


    private Balance getBalance(String id) {
        return balanceRepository.findById(id).orElseThrow(BalanceNotFound::new);
    }

}
