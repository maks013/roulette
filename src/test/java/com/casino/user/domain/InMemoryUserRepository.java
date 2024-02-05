package com.casino.user.domain;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryUserRepository implements UserRepository{

    Map<Long, User> inMemoryRepo = new ConcurrentHashMap<>();
    static final Long ACCOUNT_ID = 2L;

    User USER1 = new User(1L, "usernameExample", "user1@example.com",
            "password");

    public InMemoryUserRepository() {
        inMemoryRepo.put(1L, USER1);
    }


    @Override
    public boolean existsByUsername(String username) {
        return inMemoryRepo.values()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public boolean existsByEmail(String email) {
        return inMemoryRepo.values()
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public User save(User user) {
        user.setId(ACCOUNT_ID);
        inMemoryRepo.put(ACCOUNT_ID, user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return inMemoryRepo.values()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
