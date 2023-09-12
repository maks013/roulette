package com.casino.user.domain;

import java.util.Optional;

interface UserRepository {

    Optional<User> findByUsername(String username);

    User save(User user);
}
