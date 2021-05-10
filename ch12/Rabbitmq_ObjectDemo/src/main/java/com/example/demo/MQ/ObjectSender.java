package com.example.demo.MQ;

import com.example.demo.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(User user){
        System.out.println("Sender object : " + user.toString());
        this.amqpTemplate.convertAndSend("object", user);
    }
}
