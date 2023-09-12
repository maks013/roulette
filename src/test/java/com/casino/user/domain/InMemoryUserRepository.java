package com.casino.user.domain;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

class InMemoryUserRepository implements UserRepository {

    private final HashMap<String, User> inMemoryDatabase = new HashMap<>();
    private static final String ACCOUNT_ID = UUID.randomUUID().toString();

    @Override
    public Optional<User> findByUsername(String username) {
        return inMemoryDatabase.values()
                .stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }
    
    @Override
    public User save(User userData) {
        User user = new User(
                ACCOUNT_ID,
                userData.username(),
                userData.password()
        );
        inMemoryDatabase.put(ACCOUNT_ID, user);

        return user;
    }

}
