package com.casino.bet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findAllByUserId(Long userId);

    List<Bet> findAllByGameId(Long gameId);

    List<Bet> findAllByGameIdAndUserId(Long userId, Long gameId);
}
