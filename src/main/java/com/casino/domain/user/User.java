package com.casino.domain.user;

import lombok.Builder;

record User(
        String id,
        String username,
        String password,
        int chipWorthFive,
        int chipWorthTwentyFive,
        int chipWorthHundred,
        int gamesPlayed,
        int gamesWon
) {
    @Builder
    User {
    }

    User withChipWorthFive(int chipWorthFive) {
        return new User(
                this.id,
                this.username,
                this.password,
                chipWorthFive,
                this.chipWorthTwentyFive,
                this.chipWorthHundred,
                this.gamesPlayed,
                this.gamesWon
        );
    }

    User withChipWorthTwentyFive(int chipWorthTwentyFive) {
        return new User(
                this.id,
                this.username,
                this.password,
                this.chipWorthFive,
                chipWorthTwentyFive,
                this.chipWorthHundred,
                this.gamesPlayed,
                this.gamesWon
        );
    }

    User withChipWorthHundred(int chipWorthHundred) {
        return new User(
                this.id,
                this.username,
                this.password,
                this.chipWorthFive,
                this.chipWorthTwentyFive,
                chipWorthHundred,
                this.gamesPlayed,
                this.gamesWon
        );
    }
}
