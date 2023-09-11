package com.casino.domain.user;

import com.casino.domain.user.dto.*;
import com.casino.domain.user.exception.TransferFailedException;
import com.casino.domain.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    private final InMemoryUserRepository repository = new InMemoryUserRepository();
    private final UserService service = new UserService(repository);

    UserFacade userFacade = new UserFacade(repository, service);

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
                () -> assertEquals(userDto.username(), "username"),
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
        assertEquals(userDto.username(), "username");
    }

    @Test
    void should_show_user_stats() {
        //given
        RegisterDto registerDto = new RegisterDto("username", "password");
        userFacade.register(registerDto);

        //when
        String userId = userFacade.findByUsername("username").id();
        StatsResponseDto statsResponseDto = userFacade.showUserStats(userId);

        //then
        assertEquals(statsResponseDto.chipWorthFive(), 0);
    }

    @Test
    void should_deposit_chips_to_user_account() {
        //given
        RegisterDto registerDto = new RegisterDto("username", "password");
        userFacade.register(registerDto);

        //when
        DepositRequestDto firstDeposit = new DepositRequestDto(3, 4, 5);
        String userId = userFacade.findByUsername("username").id();
        DepositResponseDto depositResponseDto = userFacade.deposit(userId, firstDeposit);
        StatsResponseDto statsResponseDto = userFacade.showUserStats(userId);

        //then
        assertAll(
                () -> assertEquals(statsResponseDto.chipWorthFive(), 3),
                () -> assertEquals(statsResponseDto.chipWorthTwentyFive(), 4),
                () -> assertEquals(statsResponseDto.chipWorthHundred(), 5),
                () -> assertTrue(depositResponseDto.deposited())
        );
    }


    @Test
    void should_transfer_chips_to_another_account() {
        //given
        RegisterDto registerDtoUserFrom = new RegisterDto("userFrom", "password1");
        RegisterDto registerDtoUserTo = new RegisterDto("userTo", "password2");

        userFacade.register(registerDtoUserFrom);
        userFacade.register(registerDtoUserTo);

        //when
        DepositRequestDto firstDeposit = new DepositRequestDto(3, 4, 5);

        String userIdFrom = userFacade.findByUsername("userFrom").id();
        DepositResponseDto depositResponseDto = userFacade.deposit(userIdFrom, firstDeposit);

        String userIdTo = userFacade.findByUsername("userTo").id();

        TransferRequestDto transferToSecondUser = new TransferRequestDto(userIdTo, 2, 3, 4);

        TransferResponseDto transferResponseDto = userFacade.transferById(userIdFrom, transferToSecondUser);

        StatsResponseDto statsResponseDtoFrom = userFacade.showUserStats(userIdFrom);
        StatsResponseDto statsResponseDtoTo = userFacade.showUserStats(userIdTo);

        //then
        assertAll(
                () -> assertEquals(statsResponseDtoFrom.chipWorthFive(), 1),
                () -> assertEquals(statsResponseDtoFrom.chipWorthTwentyFive(), 1),
                () -> assertEquals(statsResponseDtoFrom.chipWorthHundred(), 1),
                () -> assertEquals(statsResponseDtoTo.chipWorthFive(), 2),
                () -> assertEquals(statsResponseDtoTo.chipWorthTwentyFive(), 3),
                () -> assertEquals(statsResponseDtoTo.chipWorthHundred(), 4),
                () -> assertTrue(depositResponseDto.deposited()),
                () -> assertTrue(transferResponseDto.transfered()),
                () -> assertEquals(transferResponseDto.username(), "userTo")
        );
    }

    @Test
    void should_throw_exception_when_not_enough_chips_to_transfer() {
        //given
        RegisterDto registerDtoUserFrom = new RegisterDto("userFrom", "password1");
        RegisterDto registerDtoUserTo = new RegisterDto("userTo", "password2");

        userFacade.register(registerDtoUserFrom);
        userFacade.register(registerDtoUserTo);

        //when
        DepositRequestDto firstDeposit = new DepositRequestDto(3, 4, 5);

        String userIdFrom = userFacade.findByUsername("userFrom").id();
        userFacade.deposit(userIdFrom, firstDeposit);

        String userIdTo = userFacade.findByUsername("userTo").id();

        TransferRequestDto transferToSecondUser = new TransferRequestDto(userIdTo, 4, 3, 4);

        //then
        assertThrows(TransferFailedException.class, () -> userFacade.transferById(userIdFrom, transferToSecondUser));
    }
}
