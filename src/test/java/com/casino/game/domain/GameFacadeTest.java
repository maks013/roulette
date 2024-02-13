package com.casino.game.domain;

import com.casino.game.dto.GameDto;
import com.casino.game.exception.GameNotFound;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GameFacadeTest {

    private final GameFacadeConfigForTests gameFacadeConfigForTests = new GameFacadeConfigForTests();
    private final GameFacade gameFacade = gameFacadeConfigForTests.gameFacade();

    @Test
    void should_roll_and_save_game_properly() {
        //given
        //when
        GameDto gameDto = gameFacade.roll();
        //then
        assertAll(
                () -> assertEquals(1L, gameDto.id()),
                () -> assertEquals(LocalDateTime.now().getMinute(), gameDto.roundStart().getMinute()),
                () -> assertEquals(LocalDateTime.now().plusMinutes(1).getMinute(), gameDto.roundEnd().getMinute())
        );
    }

    @Test
    void should_find_game_by_id() {
        //given
        final Long gameId = gameFacade.roll().id();
        //when
        GameDto gameDto = gameFacade.findGameById(gameId);
        //then
        assertAll(
                () -> assertEquals(gameId, gameDto.id()),
                () -> assertEquals(LocalDateTime.now().getMinute(), gameDto.roundStart().getMinute()),
                () -> assertEquals(LocalDateTime.now().plusMinutes(1).getMinute(), gameDto.roundEnd().getMinute())
        );
    }

    @Test
    void should_throw_exception_when_find_game_by_not_existing_id() {
        //given
        final Long notExistingGameId = 9999L;
        //when
        //then
        assertThrows(GameNotFound.class, () -> gameFacade.findGameById(notExistingGameId));
    }

    @Test
    void should_find_all_3_rolled_games(){
        //given
        final int sizeBeforeGames = gameFacade.findAllGames().size();
        //when
        gameFacade.roll();
        gameFacade.roll();
        gameFacade.roll();
        final int sizeAfterGames = gameFacade.findAllGames().size();
        //then
        assertEquals(3,sizeAfterGames-sizeBeforeGames);
    }
}
