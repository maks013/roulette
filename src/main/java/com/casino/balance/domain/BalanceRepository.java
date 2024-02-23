package com.casino.balance.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface BalanceRepository extends CrudRepository<Balance, Long> {
}
