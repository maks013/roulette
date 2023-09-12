package com.casino.user.domain;

import lombok.Builder;

record User(
        String id,
        String username,
        String password
) {
    @Builder
    User {
    }
}
