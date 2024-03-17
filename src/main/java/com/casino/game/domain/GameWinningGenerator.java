package com.casino.game.domain;

import com.casino.bet.domain.BetType;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class GameWinningGenerator {

    BetType generateWinningBetType() {
        Random random = new Random();
        int randomNumber = random.nextInt(15);

        return (randomNumber == 0) ? BetType.GREEN :
                (randomNumber <= 7) ? BetType.BLACK : BetType.RED;
    }

}
