package com.casino.game.domain;

import com.casino.bet.domain.BetType;
import com.casino.game.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Game {

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
