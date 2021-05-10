package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testView(){
        System.out.println(stringRedisTemplate.opsForValue().get("name::36"));
    }
}
