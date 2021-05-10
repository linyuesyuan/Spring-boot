package com.example.demo;

import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class TestEncoder {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    public void test(){
        String username = "yue";
        String password = "yue";
        SysUser user = sysUserRepository.findByName(username);
        System.out.println("username: " + user.getUsername() + ", password: " + user.getPassword());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPassword = bCryptPasswordEncoder.encode(password);
        Boolean isMatch = bCryptPasswordEncoder.matches("yue", hashPassword);
        System.out.println(isMatch);
    }
}
