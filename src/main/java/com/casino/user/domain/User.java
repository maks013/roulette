package com.casino.user.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class User {
    String id;
    String username;
    String password;
}
