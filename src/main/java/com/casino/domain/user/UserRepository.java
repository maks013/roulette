package com.casino.domain.user;

import java.util.Optional;

interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<User> findById(String id);

    User save(User user);
}
