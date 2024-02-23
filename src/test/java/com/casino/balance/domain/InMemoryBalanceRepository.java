package com.casino.balance.domain;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryBalanceRepository implements BalanceRepository {

    Map<Long, Balance> inMemoryRepo = new ConcurrentHashMap<>();

    Balance BALANCE1 = new Balance(1L, BalanceValue.emptyForNewAccount(), 1L);

    public InMemoryBalanceRepository() {
        inMemoryRepo.put(1L, BALANCE1);
    }

    @Override
    public <S extends Balance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Balance> findById(Long id) {
        return Optional.ofNullable(inMemoryRepo.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Balance> findAll() {
        return null;
    }

    @Override
    public Iterable<Balance> findAllById(Iterable<Long> longs) {
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
    public void delete(Balance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Balance> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Balance save(Balance balance) {
        balance.setId(balance.getId());
        inMemoryRepo.put(balance.getId(), balance);
        return balance;
    }
}
