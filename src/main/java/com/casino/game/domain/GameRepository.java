package com.casino.game.domain;

import java.util.List;
import java.util.Optional;

interface GameRepository {

    Game save(Game game);

    Optional<Game> findById(Long id);

    List<Game> findAll();

}
