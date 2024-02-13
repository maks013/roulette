package com.casino.bet.domain;

import com.casino.balance.domain.BalanceFacade;
import com.casino.balance.dto.Withdraw;
import com.casino.bet.dto.BetDto;
import com.casino.bet.dto.BetPlacedResult;
import com.casino.bet.dto.PlaceBet;
import com.casino.bet.exception.BetNotFound;
import com.casino.bet.exception.InvalidBetCombination;
import com.casino.game.domain.GameFacade;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class BetFacade {

    private final BetRepository repository;
    private final BalanceFacade balanceFacade;
    private final GameFacade gameFacade;

    public BetPlacedResult placeBet(PlaceBet placeBet) {
        final LocalDateTime timeOfPlacement = LocalDateTime.now();
        Long gameId = gameFacade.findGameByTime(timeOfPlacement).id();

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
                .timeOfPlacement(betDto.timeOfPlacement())
                .betType(betDto.betType())
                .build();
    }

    List<BetDto> findAllBets() {
        return repository.findAll()
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    List<BetDto> findAllBetsByUserId(Long userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    List<BetDto> findAllBetsByGameId(Long gameId) {
        return repository.findAllByGameId(gameId)
                .stream()
                .map(Bet::toDto)
                .toList();
    }

    BetDto findBetById(Long id) {
        return repository.findById(id)
                .orElseThrow(BetNotFound::new).toDto();
    }

    List<BetDto> findAllBetsByGameIdAndUserId(Long gameId, Long userId) {
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
}
