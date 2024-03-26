package com.casino.bet.domain;

import com.casino.balance.domain.BalanceFacade;
import com.casino.balance.domain.BalanceFacadeConfigForTests;
import com.casino.balance.dto.Deposit;
import com.casino.bet.dto.BetPlacedResult;
import com.casino.bet.dto.PlaceBet;
import com.casino.game.domain.GameFacade;
import com.casino.game.domain.GameFacadeConfigForTests;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BetFacadeTest {

    private final BalanceFacade balanceFacade = new BalanceFacadeConfigForTests().balanceFacade();
    private final GameFacade gameFacade = new GameFacadeConfigForTests().gameFacade();

    private final BetFacade betFacade = new BetFacade(
            new InMemoryBetRepository(),
            balanceFacade,
            gameFacade
    );

    @Test
    void should_place_bet_properly() {
        //given
        Deposit deposit = new Deposit(1L, BigDecimal.valueOf(99));
        PlaceBet placeBet = new PlaceBet(1L, BigDecimal.valueOf(10), BetType.BLACK);
        //when
        balanceFacade.deposit(deposit);
        gameFacade.roll();
        BetPlacedResult betPlacedResult = betFacade.placeBet(placeBet);
        //then
        assertAll(
                () -> assertTrue(betPlacedResult.placed()),
                () -> assertEquals(BetType.BLACK.toString(), betPlacedResult.betType())
        );
    }

    @Test
    void should_find_all_2_bets() {
        //given
        Deposit deposit = new Deposit(1L, BigDecimal.valueOf(99));
        PlaceBet placeBet = new PlaceBet(1L, BigDecimal.valueOf(10), BetType.BLACK);
        PlaceBet placeBet2 = new PlaceBet(1L, BigDecimal.valueOf(10), BetType.GREEN);
        //when
        balanceFacade.deposit(deposit);
        gameFacade.roll();
        final int sizeBeforeBets = betFacade.findAllBets().size();
        betFacade.placeBet(placeBet);
        betFacade.placeBet(placeBet2);
        final int sizeAfterBets = betFacade.findAllBets().size();
        //then
        assertEquals(2, sizeAfterBets - sizeBeforeBets);
    }


    @Test
    void should_find_all_2_bets_by_userId_and_also_gameId() {
        //given
        Deposit deposit = new Deposit(1L, BigDecimal.valueOf(99));
        PlaceBet placeBet = new PlaceBet(1L, BigDecimal.valueOf(10), BetType.BLACK);
        PlaceBet placeBet2 = new PlaceBet(1L, BigDecimal.valueOf(10), BetType.GREEN);
        //when
        balanceFacade.deposit(deposit);
        gameFacade.roll();
        betFacade.placeBet(placeBet);
        betFacade.placeBet(placeBet2);
        //then
        assertAll(
                () -> assertEquals(2, betFacade.findAllBetsByUserId(1L).size())
        );
    }

    @Test
    void should_find_bet_by_id() {
        //given
        Deposit deposit = new Deposit(1L, BigDecimal.valueOf(99));
        PlaceBet placeBet = new PlaceBet(1L, BigDecimal.valueOf(10), BetType.BLACK);
        //when
        balanceFacade.deposit(deposit);
        gameFacade.roll();
        BetPlacedResult betPlacedResult = betFacade.placeBet(placeBet);
        //then
        assertAll(
                () -> assertEquals(betPlacedResult.id(), betFacade.findBetById(1L).id()),
                () -> assertEquals(betPlacedResult.placedAmount(), betFacade.findBetById(1L).amount()),
                () -> assertEquals(betPlacedResult.betType(), betFacade.findBetById(1L).betType())
        );
    }
}
