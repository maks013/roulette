package com.casino.domain.user;


import com.casino.domain.user.dto.*;
import com.casino.domain.user.dto.DepositResponseDto;
import com.casino.domain.user.exception.TransferFailedException;
import com.casino.domain.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class UserService {

    private final UserRepository userRepository;

    StatsResponseDto showUserStats(String userId) {
        UserDto user = findById(userId);

        return StatsResponseDto.builder()
                .chipWorthFive(user.chipWorthFive())
                .chipWorthTwentyFive(user.chipWorthTwentyFive())
                .chipWorthHundred(user.chipWorthHundred())
                .gamesPlayed(user.gamesPlayed())
                .gamesWon(user.gamesWon())
                .build();
    }

    DepositResponseDto deposit(String userId, DepositRequestDto depositRequestDto) {
        User user = UserMapper.mapFromUserDto(findById(userId));

        final int chipsFive = depositRequestDto.chipWorthFive();
        final int chipsTwentyFive = depositRequestDto.chipWorthTwentyFive();
        final int chipsHundred = depositRequestDto.chipWorthHundred();

        user = updateUserChips(user, chipsFive, chipsTwentyFive, chipsHundred);

        userRepository.save(user);

        return DepositResponseDto.builder()
                .deposited(true)
                .build();
    }

    TransferResponseDto transfer(String userIdFrom, TransferRequestDto transferRequestDto) {
        User userFrom = UserMapper.mapFromUserDto(findById(userIdFrom));
        User userTo = UserMapper.mapFromUserDto(findById(transferRequestDto.id()));

        if (canTransferChips(userFrom, transferRequestDto)) {
            final int chipsFive = transferRequestDto.chipWorthFive();
            final int chipsTwentyFive = transferRequestDto.chipWorthTwentyFive();
            final int chipsHundred = transferRequestDto.chipWorthHundred();

            userFrom = updateUserChips(userFrom, -chipsFive, -chipsTwentyFive, -chipsHundred);
            userTo = updateUserChips(userTo, chipsFive, chipsTwentyFive, chipsHundred);

            userRepository.save(userFrom);
            userRepository.save(userTo);

            return TransferResponseDto.builder()
                    .transfered(true)
                    .username(userTo.username())
                    .build();
        } else {
            throw new TransferFailedException();
        }
    }

    private boolean canTransferChips(User userFrom, TransferRequestDto transferRequestDto) {
        return userFrom.chipWorthFive() >= transferRequestDto.chipWorthFive()
                && userFrom.chipWorthTwentyFive() >= transferRequestDto.chipWorthTwentyFive()
                && userFrom.chipWorthHundred() >= transferRequestDto.chipWorthHundred();
    }

    private User updateUserChips(User user, int chipsFive, int chipsTwentyFive, int chipsHundred) {
        return user.withChipWorthFive(user.chipWorthFive() + chipsFive)
                .withChipWorthTwentyFive(user.chipWorthTwentyFive() + chipsTwentyFive)
                .withChipWorthHundred(user.chipWorthHundred() + chipsHundred);
    }

    private UserDto findById(String id) {
        return userRepository.findById(id)
                .map(user -> new UserDto(
                        user.id(),
                        user.username(),
                        user.password(),
                        user.chipWorthFive(),
                        user.chipWorthTwentyFive(),
                        user.chipWorthHundred(),
                        user.gamesPlayed(), user.gamesWon()))
                .orElseThrow(UserNotFoundException::new);
    }
}
