package com.example.demo.mq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CustomSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void sendMsg(String queueName, String msg){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("convertAndSend執行之前-訊息發送時間：" + simpleDateFormat.format(new Date()));
        rabbitTemplate.convertAndSend("delayed_exchange", queueName, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 50000);
                return message;
            }
        });
    }
}
