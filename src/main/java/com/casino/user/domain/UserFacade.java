package com.casino.user.domain;

import com.casino.user.dto.*;
import com.casino.user.exception.InvalidUserRegistration;
import com.casino.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDto(
                        user.id(),
                        user.username(),
                        user.password()))
                .orElseThrow(UserNotFoundException::new);
    }

    public RegistrationResultDto register(RegisterDto registerDto) {
        final User user;
        if (registerDto.username() == null || registerDto.password() == null) {
            throw new InvalidUserRegistration();
        } else {
            user = User.builder()
                    .username(registerDto.username())
                    .password(registerDto.password())
                    .build();
        }
        User userResult = userRepository.save(user);
        return new RegistrationResultDto(userResult.id(), true, userResult.username());
    }
}
