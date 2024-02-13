package com.casino.game.domain;

public class GameFacadeConfigForTests {

    private final GameRepository gameRepository = new InMemoryGameRepository();
    private final GameWinningGenerator gameWinningGenerator  = new GameWinningGenerator();

    public GameFacade gameFacade(){
        return new GameFacade(gameRepository, gameWinningGenerator);
    }

}
