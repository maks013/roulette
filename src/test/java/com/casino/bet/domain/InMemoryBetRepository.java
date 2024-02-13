package com.casino.bet.domain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryBetRepository implements BetRepository {

    Map<Long, Bet> inMemoryRepo = new ConcurrentHashMap<>();

    Long idCounter = 1L;

    @Override
    public Bet save(Bet bet) {
        final Long id = idCounter++;
        bet.setId(id);
        inMemoryRepo.put(id, bet);
        return bet;
    }

    @Override
    public Optional<Bet> findById(Long id) {
        return Optional.ofNullable(inMemoryRepo.get(id));
    }

    @Override
    public List<Bet> findAll() {
        return inMemoryRepo.values().stream().toList();
    }

    @Override
    public List<Bet> findAllByUserId(Long userId) {
        return inMemoryRepo.values()
                .stream()
                .filter(bet -> bet.getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<Bet> findAllByGameId(Long gameId) {
        return inMemoryRepo.values()
                .stream()
                .filter(bet -> bet.getGameId().equals(gameId))
                .toList();
    }

    @Override
    public List<Bet> findAllByGameIdAndUserId(Long userId, Long gameId) {
        return inMemoryRepo.values().stream()
                .filter(bet -> bet.getGameId().equals(gameId) && bet.getUserId().equals(userId))
                .toList();
    }
}
