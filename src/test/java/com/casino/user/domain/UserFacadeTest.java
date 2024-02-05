package com.casino.user.domain;

import com.casino.user.dto.RegisterDto;
import com.casino.user.dto.RegistrationResultDto;
import com.casino.user.dto.UserDto;
import com.casino.user.exception.InvalidEmailFormat;
import com.casino.user.exception.TakenEmail;
import com.casino.user.exception.TakenUsername;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

    private final UserFacade userFacade = new UserFacade(
            inMemoryUserRepository,
            new UserDataValidatorForTests(inMemoryUserRepository)
    );

    @Test
    void should_throw_exception_when_register_with_invalid_email_format() {
        //given
        final String invalidEmail = "invalidExample";
        final RegisterDto registerDto = new RegisterDto("example", invalidEmail, "password");
        //when
        //then
        assertThrows(InvalidEmailFormat.class, () -> userFacade.register(registerDto));
    }

    @Test
    void should_throw_exception_when_register_with_already_taken_email() {
        //given
        final String takenEmail = "user1@example.com";
        final RegisterDto registerDto = new RegisterDto("example", takenEmail, "password");
        //when
        //then
        assertThrows(TakenEmail.class, () -> userFacade.register(registerDto));
    }

    @Test
    void should_throw_exception_when_register_with_already_taken_username() {
        //given
        final String takenUsername = "usernameExample";
        final RegisterDto registerDto = new RegisterDto(takenUsername, "email@example.com", "password");
        //when
        //then
        assertThrows(TakenUsername.class, () -> userFacade.register(registerDto));
    }

    @Test
    void should_register_user_successfully() {
        //given
        final String username = "newUser";
        final RegisterDto registerDto = new RegisterDto(username, "email@example.com", "password");
        //when
        RegistrationResultDto registrationResult = userFacade.register(registerDto);
        //then
        assertAll(
                () -> assertEquals(InMemoryUserRepository.ACCOUNT_ID, registrationResult.id()),
                () -> assertTrue(registrationResult.created()),
                () -> assertEquals(username, registrationResult.username())
        );
    }

    @Test
    void should_find_user_by_username() {
        //given
        final String username = "newUser", password = "password";
        final RegisterDto registerDto = new RegisterDto(username, "email@example.com", password);
        //when
        RegistrationResultDto registrationResult = userFacade.register(registerDto);
        UserDto userDto = userFacade.findUserByUsername(username);
        //then
        assertAll(
                () -> assertEquals(registrationResult.id(), userDto.id()),
                () -> assertEquals(username, userDto.username()),
                () -> assertEquals(password, userDto.password())
        );
    }
}
