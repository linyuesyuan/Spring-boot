package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Queue1")
public class ReceiverA {
    @RabbitHandler
    public void QueueReceiver(String Queue1){
        System.out.println("Receiver A: " + Queue1);
    }
}
