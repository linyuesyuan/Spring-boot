package com.example.demo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderA {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(String context){
        System.out.println("Sender: " + context);
        this.amqpTemplate.convertAndSend("Queue1", context);
    }
}
