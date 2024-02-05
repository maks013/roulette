package com.casino.user.domain;

import java.util.Optional;

interface UserRepository {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User save(User user);

    Optional<User> findByUsername(String username);
}

