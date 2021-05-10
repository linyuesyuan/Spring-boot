package com.example.demo.controller;

import com.example.demo.MQ.ObjectSender;
import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MQtest {
    @Autowired
    private ObjectSender objectSender;
    @Test
    public void sendObjectController(){
        try {
            User user = new User();
            user.setName("yue");
            user.setAge("25");
            objectSender.send(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
