package com.casino.user.domain;


import com.casino.user.dto.RegisterDto;
import com.casino.user.dto.RegistrationResultDto;
import com.casino.user.dto.UserDto;
import com.casino.user.exception.InvalidUserRegistration;
import com.casino.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private static final UserFacade userFacade = new UserFacade(new InMemoryUserRepository());

    @Test
    void should_throw_exception_when_user_not_found() {
        //given
        //when
        //then
        assertThrows(UserNotFoundException.class, () -> userFacade.findByUsername("username"));
    }

    @Test
    void should_register_user() {
        //given
        RegisterDto registerDto = new RegisterDto("username", "password");
        RegistrationResultDto registrationResultDto = userFacade.register(registerDto);

        //when
        UserDto userDto = userFacade.findByUsername("username");

        //then
        assertAll(
                () -> assertEquals("username", userDto.username()),
                () -> assertTrue(registrationResultDto.created())
        );
    }

    @Test
    void should_register_and_find_user_by_username() {
        //given
        RegisterDto registerDto = new RegisterDto("username", "password");
        userFacade.register(registerDto);

        //when
        UserDto userDto = userFacade.findByUsername("username");

        //then
        assertEquals("username",userDto.username());
    }

    @Test
    void should_throw_exception_when_user_registration_data_is_null() {
        //given
        RegisterDto registerDto = new RegisterDto(null, "password");

        //when
        //then
        assertThrows(InvalidUserRegistration.class, () -> userFacade.register(registerDto));
    }

}
