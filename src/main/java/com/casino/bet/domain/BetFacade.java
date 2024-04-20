package com.casino.bet.domain;

import com.casino.balance.domain.BalanceFacade;
import com.casino.balance.dto.Deposit;
import com.casino.balance.dto.Withdraw;
import com.casino.bet.dto.BetDto;
import com.casino.bet.dto.BetPlacedResult;
import com.casino.bet.dto.PlaceBet;
import com.casino.bet.exception.BetNotFound;
import com.casino.bet.exception.InvalidBetCombination;
import com.casino.game.domain.GameFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class BetFacade {

    private final BetRepository repository;
    private final BalanceFacade balanceFacade;
    private final GameFacade gameFacade;

    public BetPlacedResult placeBet(PlaceBet placeBet) {
        final LocalDateTime timeOfPlacement = LocalDateTime.now();
        Long gameId = getGameId(timeOfPlacement) + 1L;

        if (!isUserBetAllowed(findAllBetsByGameIdAndUserId(gameId, placeBet.userId()), placeBet.betType())) {
            throw new InvalidBetCombination();
        }

        balanceFacade.withdraw(new Withdraw(placeBet.userId(), placeBet.amount()));

        Bet bet = Bet.builder().amount(placeBet.amount()).betType(placeBet.betType())
                .timeOfPlacement(timeOfPlacement).userId(placeBet.userId()).gameId(gameId).build();

        BetDto betDto = repository.save(bet).toDto();

        return BetPlacedResult.builder()
                .id(betDto.id())
                .placed(true)
                .placedAmount(betDto.amount())
                .timeOfPlacement(timeOfPlacement)
                .betType(betDto.betType())
                .gameId(gameId)
                .build();
    }

    public void calculateDrawResults(LocalDateTime localDateTime) {
        final Long gameId = getGameId(localDateTime);
        final String winningBetType = gameFacade.findGameById(gameId).winningBetType();

        List<BetDto> winningBets = findAllBetsByGameId(gameId).stream()
                .filter(bet -> bet.betType().equals(winningBetType))
                .toList();

        for (BetDto bet : winningBets) {
            BigDecimal wonAmount = BetType.valueOf(bet.betType()).calculateWinningAmount(bet.amount());
            Deposit depositWonAmount = new Deposit(bet.userId(), wonAmount);

            balanceFacade.deposit(depositWonAmount);
        }
    }

    public List<BetDto> findAllBets() {
        return repository.findAll()
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    public List<BetDto> findAllBetsByUserId(Long userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    public List<BetDto> findAllBetsByGameId(Long gameId) {
        return repository.findAllByGameId(gameId)
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    public BetDto findBetById(Long id) {
        return repository.findById(id)
                .orElseThrow(BetNotFound::new).toDto();
    }

    public List<BetDto> findAllBetsByGameIdAndUserId(Long gameId, Long userId) {
        return repository.findAllByGameIdAndUserId(gameId, userId)
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    private boolean isUserBetAllowed(List<BetDto> userBets, BetType newBetType) {
        boolean redExists = userBets.stream().anyMatch(bet -> bet.betType().equals(BetType.RED.toString()));
        boolean blackExists = userBets.stream().anyMatch(bet -> bet.betType().equals(BetType.BLACK.toString()));

        return (newBetType != BetType.RED || !blackExists) && (newBetType != BetType.BLACK || !redExists);
    }

    private Long getGameId(LocalDateTime localDateTime) {
        return gameFacade.findGameByTime(localDateTime).id();
    }
}
