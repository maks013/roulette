package com.casino.game.domain;

import com.casino.game.dto.GameDto;
import com.casino.game.exception.GameNotFound;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class GameFacade {

    private final GameRepository repository;
    private final GameWinningGenerator gameWinningGenerator;

    public GameDto roll(){
        final Game game = Game.builder()
                .winningBetType(gameWinningGenerator.generateWinningBetType())
                .roundStart(LocalDateTime.now())
                .roundEnd(LocalDateTime.now().plusMinutes(1))
                .build();

        return repository.save(game).toDto();
    }

    public GameDto findGameByTime(LocalDateTime localDateTime) {
        return repository.findAll()
                .stream()
                .filter(game -> !localDateTime.isBefore(game.getRoundStart())
                        && !localDateTime.isAfter(game.getRoundEnd()))
                .findFirst()
                .map(Game::toDto)
                .orElseThrow(GameNotFound::new);
    }

    public GameDto findGameById(Long id){
        return repository.findById(id)
                .orElseThrow(GameNotFound::new).toDto();
    }

    public List<GameDto> findAllGames(){
        return repository.findAll()
                .stream()
                .map(Game::toDto)
                .toList();
    }
}
