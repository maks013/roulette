package com.casino.balance.domain;

import java.util.Optional;

interface BalanceRepository {

    Optional<Balance> findById(String id);

    Balance save(Balance balance);
}
