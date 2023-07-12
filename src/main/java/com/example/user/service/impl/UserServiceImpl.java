package com.example.user.service.impl;

import com.example.user.convertor.UserConvertor;
import com.example.user.domain.User;
import com.example.user.dtos.UserDto;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConvertor userConvertor;
    @Override
    public UserDto create(UserDto userDto){
        User user=userConvertor.convert(userDto);
        User afterSave=userRepository.save(user);
        return userConvertor.convert(afterSave);
    }

    @Override
    public List<UserDto> getUserByName(String name) {
        List<User> users= userRepository.getByName(name);
        if(Objects.isNull(users))return null;
        List<UserDto> ans=new ArrayList<>();
        for(User user:users){
            ans.add(userConvertor.convert(user));
        }
        return ans;
    }
}
