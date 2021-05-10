package com.example.demo.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    public void send(){
        String context = "Fanout";
        System.out.println("Sender: " + context);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "", context);
    }
}
