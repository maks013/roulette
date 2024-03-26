package com.casino.game.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryGameRepository implements GameRepository {

    Map<Long, Game> inMemoryRepo = new ConcurrentHashMap<>();

    Long idCounter = 1L;

    @Override
    public Game save(Game game) {
        Long id = idCounter++;
        game.setId(id);
        inMemoryRepo.put(id, game);
        return game;
    }

    @Override
    public Optional<Game> findById(Long id) {
        return Optional.ofNullable(inMemoryRepo.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Game> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Game> findAll() {
        return inMemoryRepo.values().stream().toList();
    }

    @Override
    public List<Game> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Game entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Game> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Game> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Game> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Game> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Game getOne(Long aLong) {
        return null;
    }

    @Override
    public Game getById(Long aLong) {
        return null;
    }

    @Override
    public Game getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Game> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Game> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Game> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Game> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Game> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Game> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Game, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Game> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Game> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Game> findByTime(LocalDateTime time) {
        return inMemoryRepo.values().stream()
                .filter(game -> !time.isBefore(game.getRoundStart()) && !time.isAfter(game.getRoundEnd()))
                .findFirst();
    }
}
