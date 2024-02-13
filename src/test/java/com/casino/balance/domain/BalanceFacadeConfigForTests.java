package com.casino.balance.domain;

public class BalanceFacadeConfigForTests {

    InMemoryBalanceRepository inMemoryBalanceRepository = new InMemoryBalanceRepository();

    public BalanceFacade balanceFacade(){
        return new BalanceFacade(inMemoryBalanceRepository);
    }

}
