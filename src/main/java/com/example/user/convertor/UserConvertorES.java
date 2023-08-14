package com.example.user.convertor;

import com.example.user.domain.User;
import com.example.user.domain.UserES;

public class UserConvertorES {

    public UserES convert(User user) {
        UserES userES = new UserES();
        userES.setId(user.getId());
        userES.setName(user.getName());
        userES.setAge(user.getAge());
        return userES;
    }
}
