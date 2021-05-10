package com.example.demo.controller;

import com.example.demo.mq.SenderA;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MQtest {
    @Autowired
    private SenderA queueSender;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void QueueSend(){
        int i = 2;
        for (int j = 0; j < i; j++){
            String msg = "Queue1 msg" + j + new Date();
            try {
                queueSender.send(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
