package com.casino.user.domain;

import com.casino.balance.domain.BalanceFacade;
import com.casino.user.dto.RegisterDto;
import com.casino.user.dto.RegistrationResultDto;
import com.casino.user.dto.UserDto;
import com.casino.user.exception.InvalidUserRegistration;
import com.casino.user.exception.UserNotFound;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFacade {

    private final UserRepository repository;
    private final UserValidationService userValidationService;
    private final BalanceFacade balanceFacade;
    private final PasswordEncoder bCryptEncoder;

    public RegistrationResultDto register(RegisterDto registerDto) {
        final User user;
        verifyRegistrationData(registerDto);

        user = User.builder()
                .username(registerDto.username())
                .email(registerDto.email())
                .password(bCryptEncoder.encode(registerDto.password()))
                .role(User.Role.USER)
                .build();

        UserDto savedUser = repository.save(user).toDto();
        balanceFacade.createBalance(savedUser.id());

        return new RegistrationResultDto(savedUser.id(), true, savedUser.username());
    }

    public UserDto findUserByUsername(String username) {
        return repository.findByUsername(username)
                .map(User::toDto)
                .orElseThrow(UserNotFound::new);
    }

    private void verifyRegistrationData(RegisterDto registerDto) {
        if (registerDto.username() == null || registerDto.password() == null) {
            throw new InvalidUserRegistration();
        }
        userValidationService.validateEmailFormat(registerDto.email());
        userValidationService.isEmailAvailable(registerDto.email());
        userValidationService.isUsernameAvailable(registerDto.username());

    }

}
