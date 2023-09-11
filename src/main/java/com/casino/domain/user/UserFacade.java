package com.casino.domain.user;

import com.casino.domain.user.dto.*;
import com.casino.domain.user.exception.InvalidUserRegistration;
import com.casino.domain.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> new UserDto(
                        user.id(),
                        user.username(),
                        user.password(),
                        user.chipWorthFive(), user.chipWorthTwentyFive(), user.chipWorthHundred(),
                        user.gamesPlayed(), user.gamesWon()))
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
                    .chipWorthFive(0)
                    .chipWorthTwentyFive(0)
                    .chipWorthHundred(0)
                    .gamesPlayed(0)
                    .gamesWon(0)
                    .build();
        }
        User userResult = userRepository.save(user);
        return new RegistrationResultDto(userResult.id(), true, userResult.username());
    }

    public StatsResponseDto showUserStats(String userId) {
        return userService.showUserStats(userId);
    }

    public DepositResponseDto deposit(String userId, DepositRequestDto depositRequestDto) {
        return userService.deposit(userId, depositRequestDto);
    }

    public TransferResponseDto transferById(String userIdFrom, TransferRequestDto transferRequestDto) {
        return userService.transfer(userIdFrom, transferRequestDto);
    }
}
