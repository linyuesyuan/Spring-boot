package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public User findUserById(long id);
    public User insertUser(User user);
    public User updateUserById(User user);
    public void deleteUserById(long id);
}
