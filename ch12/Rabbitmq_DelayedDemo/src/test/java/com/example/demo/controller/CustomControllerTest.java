package com.example.demo.controller;

import com.example.demo.mq.CustomSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

@SpringBootTest
public class CustomControllerTest {
    @Autowired
    private CustomSender customSender;
    @Test
    public void send(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf);
        System.out.println("CustomControllerTest.send-發送時間：" + sdf);
        customSender.sendMsg("delay_queue_1", "支付逾期，取消訂單通知！");
    }
}
