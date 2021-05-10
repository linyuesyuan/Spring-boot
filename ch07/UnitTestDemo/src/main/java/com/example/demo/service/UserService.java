package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getUserInfo(){
        User user = new User();
        user.setName("yue");
        user.setAge(24);
        return user;
    }
}
