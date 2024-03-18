package com.casino.game.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM games g WHERE :time BETWEEN g.roundStart AND g.roundEnd")
    Optional<Game> findByTime(LocalDateTime time);
}
