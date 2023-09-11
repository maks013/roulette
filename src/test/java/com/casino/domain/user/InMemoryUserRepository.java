package com.casino.domain.user;

import java.util.HashMap;
import java.util.Optional;

class InMemoryUserRepository implements UserRepository {

    private final HashMap<String, User> inMemoryDatabase = new HashMap<>();

    @Override
    public Optional<User> findByUsername(String username) {
        return inMemoryDatabase.values()
                .stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(inMemoryDatabase.get(id));
    }

    @Override
    public User save(User userData) {
        String generatedId = generateId(userData.username());
        User user = new User(
                generatedId,
                userData.username(),
                userData.password(),
                userData.chipWorthFive(),
                userData.chipWorthTwentyFive(),
                userData.chipWorthHundred(),
                userData.gamesPlayed(),
                userData.gamesWon()
        );
        inMemoryDatabase.put(generatedId, user);

        return user;
    }

    private String generateId(String username) {
        return username + "123";
    }
}
