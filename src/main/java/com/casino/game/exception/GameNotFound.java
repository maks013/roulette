package com.casino.game.exception;

public class GameNotFound extends RuntimeException{
    public GameNotFound(){
        super("Game not found");
    }
}
