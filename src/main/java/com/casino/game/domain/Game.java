package com.casino.game.domain;

import com.casino.bet.domain.BetType;
import com.casino.game.dto.GameDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity(name="games")
@NoArgsConstructor
@AllArgsConstructor
class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BetType winningBetType;
    private LocalDateTime roundStart;
    private LocalDateTime roundEnd;


    GameDto toDto(){
        return GameDto.builder()
                .id(id)
                .winningBetType(winningBetType.toString())
                .roundStart(roundStart)
                .roundEnd(roundEnd)
                .build();
    }
}
