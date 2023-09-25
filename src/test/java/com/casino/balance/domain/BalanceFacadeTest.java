package com.casino.balance.domain;

import com.casino.balance.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BalanceFacadeTest {

    private final BalanceFacade balanceFacade = new BalanceFacade(new InMemoryBalanceRepository());
    private BalanceDto balanceDto;

    private final String ID_ACCOUNT_FIRST = UUID.randomUUID().toString();
    private final String ID_ACCOUNT_SECOND = UUID.randomUUID().toString();

    @BeforeEach
    void setUp() {
        balanceDto = balanceFacade.createBalance(ID_ACCOUNT_FIRST);
    }

    @Test
    void should_create_balance() {
        //given
        //when
        //then
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_EVEN), balanceDto.value()),
                () -> assertEquals(ID_ACCOUNT_FIRST, balanceDto.id())
        );
    }

    @Test
    void should_deposit_value() {
        //given
        Deposit deposit = new Deposit(ID_ACCOUNT_FIRST, BigDecimal.valueOf(199));

        //when
        BalanceDto result = balanceFacade.deposit(deposit);

        //then
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(199).setScale(2, RoundingMode.HALF_EVEN), result.value()),
                () -> assertEquals(ID_ACCOUNT_FIRST, result.id())
        );
    }

    @Test
    void should_withdraw_value() {
        //given
        Deposit deposit = new Deposit(ID_ACCOUNT_FIRST, BigDecimal.valueOf(199));
        balanceFacade.deposit(deposit);
        Withdraw withdraw = new Withdraw(ID_ACCOUNT_FIRST, BigDecimal.valueOf(189));

        //when
        BalanceDto withdrawResult = balanceFacade.withdraw(withdraw);

        //then
        assertAll(
                () -> assertEquals(BigDecimal.valueOf(10).setScale(2, RoundingMode.HALF_EVEN), withdrawResult.value()),
                () -> assertEquals(ID_ACCOUNT_FIRST, withdrawResult.id())
        );
    }

    @Test
    void should_transfer_value_to_another_balance() {
        // given
        Deposit deposit = new Deposit(ID_ACCOUNT_FIRST, BigDecimal.valueOf(199));
        balanceFacade.createBalance(ID_ACCOUNT_SECOND);
        Transfer transfer = new Transfer(ID_ACCOUNT_FIRST, ID_ACCOUNT_SECOND, BigDecimal.valueOf(99));

        // when
        balanceFacade.deposit(deposit);
        TransferResult transferResult = balanceFacade.transfer(transfer);

        // then
        BalanceDto balanceDtoFrom = balanceFacade.getBalanceById(ID_ACCOUNT_FIRST);
        BalanceDto balanceDtoTo = balanceFacade.getBalanceById(ID_ACCOUNT_SECOND);

        assertAll(
                () -> assertEquals(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_EVEN), balanceDtoFrom.value()),
                () -> assertEquals(BigDecimal.valueOf(99).setScale(2, RoundingMode.HALF_EVEN), balanceDtoTo.value()),
                () -> assertEquals(ID_ACCOUNT_SECOND, balanceDtoTo.id()),
                () -> assertEquals(ID_ACCOUNT_FIRST, transferResult.fromBalanceId()),
                () -> assertEquals(ID_ACCOUNT_SECOND, transferResult.toBalanceId()),
                () -> assertTrue(transferResult.transfered())
        );
    }

}
