package com.casino.infrastructure.game.scheduler;

import com.casino.bet.domain.BetFacade;
import com.casino.game.domain.GameFacade;
import com.casino.game.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Log4j2
@AllArgsConstructor
public class GameScheduler {

    private final GameFacade gameFacade;
    private final BetFacade betFacade;

    @Scheduled(fixedRate = 60000)
    public void rollNewGameEveryMinute(){
        GameDto gameDto = gameFacade.roll();
        betFacade.calculateDrawResults(LocalDateTime.now());
        log.info("New game rolled " + gameDto);
    }
}
