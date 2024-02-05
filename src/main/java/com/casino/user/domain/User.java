package com.casino.user.domain;

import com.casino.user.dto.UserDto;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class User {

    private Long id;
    private String username;
    private String email;
    private String password;

    UserDto toDto(){
        return UserDto.builder()
                .id(id)
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
