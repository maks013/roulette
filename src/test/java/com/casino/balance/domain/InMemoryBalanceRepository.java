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
    public Optional<Balance> findById(Long id) {
        return Optional.ofNullable(inMemoryRepo.get(id));
    }

    @Override
    public Balance save(Balance balance) {
        balance.setId(balance.getId());
        inMemoryRepo.put(balance.getId(), balance);
        return balance;
    }
}
