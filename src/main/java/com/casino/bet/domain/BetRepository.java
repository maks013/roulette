package com.casino.bet.domain;

import java.util.List;
import java.util.Optional;

interface BetRepository {

    Bet save(Bet bet);

    Optional<Bet> findById(Long id);

    List<Bet> findAll();

    List<Bet> findAllByUserId(Long userId);

    List<Bet> findAllByGameId(Long gameId);

    List<Bet> findAllByGameIdAndUserId(Long userId, Long gameId);
}
