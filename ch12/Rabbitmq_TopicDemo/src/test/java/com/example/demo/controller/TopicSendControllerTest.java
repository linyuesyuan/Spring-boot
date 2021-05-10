package com.example.demo.controller;

import com.example.demo.mq.TopicSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TopicSendControllerTest {
    @Autowired
    private TopicSender topicSender;
    @Test
    public void topic() throws Exception{
        topicSender.send();
    }
    @Test
    public void topic2() throws Exception{
        topicSender.send2();
    }
    @Test
    public void topic3() throws Exception{
        topicSender.send3();
    }
}
