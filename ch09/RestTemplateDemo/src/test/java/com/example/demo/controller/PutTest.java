package com.example.demo.controller;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PutTest {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void put(){
        RestTemplate client = restTemplateBuilder.build();
        User user = new User();
        user.setName("yue");
        client.put("http://localhost:8080/{1}", user, 4);
    }
}
