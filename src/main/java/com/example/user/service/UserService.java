package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto create(UserDto userDto);
    List<UserDto> getUserByName(String name);

    List<UserDto> getUserByCode(String code);
    List<UserDto> searchByName(String name);

    UserDto update(UserDto userDto, String code);
}
