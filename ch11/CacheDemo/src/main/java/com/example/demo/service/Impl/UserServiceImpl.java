package com.example.demo.service.Impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "user")
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Cacheable(key = "#id")
    public User findUserById(long id) {
        return userRepository.findUserById(id);
    }

    @Override
    @CachePut(key = "#user.id")
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @CachePut(key = "#user.id")
    public User updateUserById(User user) {
        return userRepository.save(user);
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
