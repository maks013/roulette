package com.casino.game.domain;

import com.casino.game.dto.GameDto;
import com.casino.game.exception.GameNotFound;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class GameFacade {

    private final GameRepository repository;
    private final GameWinningGenerator gameWinningGenerator;

    public GameDto roll() {
        final Game game = Game.builder()
                .winningBetType(gameWinningGenerator.generateWinningBetType())
                .roundStart(LocalDateTime.now())
                .roundEnd(LocalDateTime.now().plusMinutes(1))
                .build();

        return repository.save(game).toDto();
    }

    public GameDto findGameByTime(LocalDateTime localDateTime) {
        return repository.findByTime(localDateTime)
                .map(Game::toDto)
                .orElseThrow(GameNotFound::new);
    }

    public GameDto findGameById(Long id) {
        return repository.findById(id)
                .map(Game::toDto)
                .orElseThrow(GameNotFound::new);
    }

    public List<GameDto> findAllGames() {
        return repository.findAll()
                .stream()
                .map(Game::toDto)
                .toList();
    }
}
