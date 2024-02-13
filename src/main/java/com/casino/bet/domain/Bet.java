package com.casino.bet.domain;

import com.casino.bet.dto.BetDto;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Bet {

    private Long id;
    private BigDecimal amount;
    private BetType betType;
    private LocalDateTime timeOfPlacement;
    private Long userId;
    private Long gameId;

    BetDto toDto(){
        return BetDto.builder()
                .id(id)
                .amount(getValueAsBigDecimal(amount))
                .betType(betType.toString())
                .userId(userId)
                .gameId(gameId)
                .build();
    }

    BigDecimal getValueAsBigDecimal(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
