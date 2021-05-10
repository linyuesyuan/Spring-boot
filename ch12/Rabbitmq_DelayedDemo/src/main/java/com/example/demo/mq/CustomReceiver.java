package com.example.demo.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RabbitListener(queues = "delay_queue_1")
public class CustomReceiver {
    @RabbitHandler
    public void receive(String msg){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("CustomReceiver-接收時間：" + simpleDateFormat.format(new Date()) + msg);
        System.out.println();
        System.out.println("Receiver : 執行取消訂單");
    }
}
