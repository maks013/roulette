package com.casino.domain.user;

record User(
        String id,
        String username,
        String password,
        Long chipWorthFive,
        Long chipWorthTwentyFive,
        Long chipWorthHundred,
        Long gamesPlayed,
        Long gamesWon
) {
}
