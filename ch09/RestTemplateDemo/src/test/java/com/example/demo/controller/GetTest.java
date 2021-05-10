package com.example.demo.controller;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetTest {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Test
    public void nParameters(){
        RestTemplate client = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = client.getForEntity("http://localhost:8080/getUser1", String.class);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void withParameters1(){
        RestTemplate client = restTemplateBuilder.build();
        ResponseEntity<String> responseEntity = client.getForEntity("http://localhost:8080/getParameter?name={1}&id={2}", String.class, "yue", 1);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void withParameters2(){
        RestTemplate client = restTemplateBuilder.build();
        Map<String, String> map = new HashMap<>();
        map.put("name", "yue");
        ResponseEntity<String> responseEntity = client.getForEntity("http://localhost:8080/getParameter?name={name}&id=3", String.class, map);
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void restUser1(){
        RestTemplate client = restTemplateBuilder.build();
        ResponseEntity<User> responseEntity = client.getForEntity("http://localhost:8080/getUser1", User.class);
        System.out.println(responseEntity.getBody().getId());
        System.out.println(responseEntity.getBody().getName());
    }

    @Test
    public void getForObject(){
        RestTemplate client = restTemplateBuilder.build();
        User user = client.getForObject("http://localhost:8080/getUser1", User.class);
        System.out.println(user.getName());
    }
}
