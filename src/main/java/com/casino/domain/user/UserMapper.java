package com.casino.domain.user;

import com.casino.domain.user.dto.UserDto;

class UserMapper {

    static User mapFromUserDto(UserDto userDto){
        return User.builder()
                .id(userDto.id())
                .username(userDto.username())
                .password(userDto.password())
                .chipWorthFive(userDto.chipWorthFive())
                .chipWorthTwentyFive(userDto.chipWorthTwentyFive())
                .chipWorthHundred(userDto.chipWorthHundred())
                .gamesPlayed(userDto.gamesPlayed())
                .gamesWon(userDto.gamesWon())
                .build();
    }

}
