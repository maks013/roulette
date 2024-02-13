package com.casino.game.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryGameRepository implements GameRepository {

    Map<Long, Game> inMemoryRepo = new ConcurrentHashMap<>();

    Long idCounter = 1L;

    @Override
    public Game save(Game game) {
        Long id = idCounter++;
        game.setId(id);
        inMemoryRepo.put(id,game);
        return game;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(inMemoryRepo.get(id));
    }

    @Override
    public List<Game> findAll() {
        return inMemoryRepo.values().stream().toList();
    }
}
