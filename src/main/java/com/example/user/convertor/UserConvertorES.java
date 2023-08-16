package com.example.user.convertor;
import com.example.user.domain.User;
import com.example.user.dtos.UserDto;
import com.example.user.domain.UserES;
import org.springframework.stereotype.Component;

@Component
public class UserConvertorES {

    public UserES convert(UserDto userDto) {
        UserES userES = UserES.Builder.userES()
                .withId(userDto.getId())
                .withName(userDto.getName())
                .withAge(userDto.getAge())
                .withCode(userDto.getCode())
                .withModificationDate(userDto.getModificationDate())
                .build();
        return userES;
    }
    public UserDto convert(UserES userES) {
        UserDto userDto = UserDto.Builder.userDto()
                .withId(userES.getId())
                .withName(userES.getName())
                .withAge(userES.getAge())
                .withCode(userES.getCode())
                .withModificationDate(userES.getModificationDate())
                .build();
        return userDto;
    }
    public UserES convert(User user) {
        UserES userES = UserES.Builder.userES()
                .withId(user.getId())
                .withName(user.getName())
                .withAge(user.getAge())
                .withCode(user.getCode())
                .withModificationDate(user.getModificationDate())
                .build();
        return userES;
    }
}
