package com.casino.balance.domain;

import com.casino.balance.dto.*;
import com.casino.balance.exception.BalanceNotFound;
import com.casino.balance.exception.InvalidBalanceCreation;
import com.casino.balance.exception.InvalidDeposit;
import com.casino.balance.exception.InvalidWithdraw;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class BalanceFacadeTest {

    private final BalanceFacade balanceFacade = new BalanceFacade(new InMemoryBalanceRepository());

    BigDecimal getValueAsBigDecimal(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }

    @Test
    void should_throw_exception_when_create_balance_for_existing_id() {
        //given
        final Long existingBalanceId = 1L;
        //when
        //then
        assertThrows(InvalidBalanceCreation.class, () -> balanceFacade.createBalance(existingBalanceId));
    }

    @Test
    void should_create_new_balance() {
        //given
        final Long newBalanceId = 2L;
        //when
        BalanceDto balanceDto = balanceFacade.createBalance(newBalanceId);
        //then
        assertAll(
                () -> assertEquals(newBalanceId, balanceDto.id()),
                () -> assertEquals(balanceDto.value(), BalanceValue.emptyForNewAccount().getValueAsBigDecimal())
        );
    }

    @Test
    void should_throw_exception_when_deposit_invalid_amount() {
        //given
        BigDecimal invalidAmount = BigDecimal.ZERO;
        //when
        Deposit deposit = new Deposit(1L, invalidAmount);
        //then
        assertThrows(InvalidDeposit.class, () -> balanceFacade.deposit(deposit));
    }

    @Test
    void should_deposit_successfully() {
        //given
        BigDecimal amount = BigDecimal.valueOf(100);
        //when
        Deposit deposit = new Deposit(1L, amount);
        BalanceDto balance = balanceFacade.deposit(deposit);
        //then
        assertAll(
                () -> assertEquals(1L, balance.id()),
                () -> assertEquals(getValueAsBigDecimal(amount), balance.value())
        );
    }

    @Test
    void should_throw_exception_when_withdraw_from_empty_balance() {
        //given
        BigDecimal amount = BigDecimal.valueOf(100);
        //when
        Withdraw withdraw = new Withdraw(1L, amount);
        //then
        assertThrows(InvalidWithdraw.class, () -> balanceFacade.withdraw(withdraw));
    }

    @Test
    void should_withdraw_successfully() {
        //given
        BigDecimal amount = BigDecimal.valueOf(100);
        //when
        Deposit deposit = new Deposit(1L, amount);
        Withdraw withdraw = new Withdraw(1L, amount);
        balanceFacade.deposit(deposit);
        BalanceDto balance = balanceFacade.withdraw(withdraw);
        //then
        assertAll(
                () -> assertEquals(1L, balance.id()),
                () -> assertEquals(getValueAsBigDecimal(BigDecimal.ZERO), balance.value())
        );
    }

    @Test
    void should_throw_exception_when_transfer_from_empty_balance() {
        //given
        BigDecimal amount = BigDecimal.valueOf(100);
        balanceFacade.createBalance(2L);
        //when
        Deposit deposit = new Deposit(1L, amount);
        Transfer transfer = new Transfer(1L, 2L, amount);
        balanceFacade.deposit(deposit);
        TransferResult transferResult = balanceFacade.transfer(transfer);
        //then
        assertTrue(transferResult.created());
    }

    @Test
    void should_throw_exception_when_get_balance_by_not_existing_id() {
        //given
        final Long notExistingId = 99L;
        //when
        //then
        assertThrows(BalanceNotFound.class,()->balanceFacade.getBalanceById(notExistingId));
    }

    @Test
    void should_get_balance_by_id() {
        //given
        final Long existingId = 1L;
        //when
        BalanceDto balanceDto = balanceFacade.getBalanceById(existingId);
        //then
        assertAll(
                () -> assertEquals(existingId,balanceDto.id()),
                () -> assertEquals(BalanceValue.emptyForNewAccount().getValueAsBigDecimal(),balanceDto.value())
        );
    }
}
