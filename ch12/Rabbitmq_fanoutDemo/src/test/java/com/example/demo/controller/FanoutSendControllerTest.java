package com.example.demo.controller;

import com.example.demo.mq.FanoutSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FanoutSendControllerTest {
    @Autowired
    private FanoutSender fanoutSender;
    @Test
    public void fanoutSender(){
        fanoutSender.send();
    }
}
