package com.casino.balance.domain;

import java.util.Optional;

interface BalanceRepository {

    Optional<Balance> findById(Long id);

    Balance save(Balance balance);
}
