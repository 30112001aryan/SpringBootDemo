package com.example.user.controller;

import com.example.user.domain.User;
import com.example.user.dtos.UserDto;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/")
    public UserDto create(@RequestBody UserDto userDto){
        return userService.create(userDto);
    }

    @GetMapping("/findByCode")
    public List<UserDto> findByCode(@RequestParam("code") String code){
        return userService.getUserByCode(code);
    }

    @GetMapping("/findByName")
    public List<UserDto> getByName(@RequestParam("name") String name){
        return userService.getUserByName(name);
    }

}
