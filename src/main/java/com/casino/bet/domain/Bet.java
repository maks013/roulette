package com.casino.bet.domain;

import com.casino.bet.dto.BetDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity(name = "bets")
@NoArgsConstructor
@AllArgsConstructor
class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private BetType betType;
    private LocalDateTime timeOfPlacement;
    private Long userId;
    private Long gameId;

    BetDto toDto() {
        return BetDto.builder()
                .id(id)
                .amount(getValueAsBigDecimal(amount))
                .betType(betType.toString())
                .timeOfPlacement(timeOfPlacement)
                .userId(userId)
                .gameId(gameId)
                .build();
    }

    BigDecimal getValueAsBigDecimal(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
