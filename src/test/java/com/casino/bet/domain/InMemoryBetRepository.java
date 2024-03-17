package com.casino.bet.domain;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

class InMemoryBetRepository implements BetRepository {

    Map<Long, Bet> inMemoryRepo = new ConcurrentHashMap<>();

    Long idCounter = 1L;

    @Override
    public Bet save(Bet bet) {
        final Long id = idCounter++;
        bet.setId(id);
        inMemoryRepo.put(id, bet);
        return bet;
    }

    @Override
    public Optional<Bet> findById(Long id) {
        return Optional.ofNullable(inMemoryRepo.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public <S extends Bet> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<Bet> findAll() {
        return inMemoryRepo.values().stream().toList();
    }

    @Override
    public List<Bet> findAllById(Iterable<Long> longs) {
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
    public void delete(Bet entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Bet> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Bet> findAllByUserId(Long userId) {
        return inMemoryRepo.values()
                .stream()
                .filter(bet -> bet.getUserId().equals(userId))
                .toList();
    }

    @Override
    public List<Bet> findAllByGameId(Long gameId) {
        return inMemoryRepo.values()
                .stream()
                .filter(bet -> bet.getGameId().equals(gameId))
                .toList();
    }

    @Override
    public List<Bet> findAllByGameIdAndUserId(Long userId, Long gameId) {
        return inMemoryRepo.values().stream()
                .filter(bet -> bet.getGameId().equals(gameId) && bet.getUserId().equals(userId))
                .toList();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Bet> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Bet> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Bet> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Bet getOne(Long aLong) {
        return null;
    }

    @Override
    public Bet getById(Long aLong) {
        return null;
    }

    @Override
    public Bet getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Bet> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Bet> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Bet> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Bet> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Bet> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Bet> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Bet, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public List<Bet> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Bet> findAll(Pageable pageable) {
        return null;
    }
}
