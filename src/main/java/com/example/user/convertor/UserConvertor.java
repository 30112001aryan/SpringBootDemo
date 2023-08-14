package com.example.user.convertor;

import com.example.user.domain.User;
import com.example.user.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConvertor {
    public UserDto convert (User user){
        UserDto userDto=UserDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .code(user.getCode())
                .build();
        return userDto;
    }
    public User convert(UserDto userDto) {
        User user = User.Builder.user()
                .withId(userDto.getId())
                .withName(userDto.getName())
                .withAge(userDto.getAge())
                .withCode(userDto.getCode())
                .withModificationDate(userDto.getModificationDate())
                .build();
        return user;
    }
}
