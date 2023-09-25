package com.casino.balance.domain;

import java.util.HashMap;
import java.util.Optional;

class InMemoryBalanceRepository implements BalanceRepository{

    private final HashMap<String, Balance> inMemoryBalanceDatabase = new HashMap<>();

    @Override
    public Optional<Balance> findById(String id) {
        return Optional.ofNullable(inMemoryBalanceDatabase.get(id));
    }

    @Override
    public Balance save(Balance balance) {
        inMemoryBalanceDatabase.put(balance.getId(),balance);
        return balance;
    }
}
