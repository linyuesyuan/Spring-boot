package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.a")
public class TopicReceiverA {
    @RabbitHandler
    public void process(String msg){
        System.out.println("Topic ReceiverA: " + msg);
    }
}
