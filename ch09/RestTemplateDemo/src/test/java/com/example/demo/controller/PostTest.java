package com.example.demo.controller;

import com.example.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostTest {
    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    public void postForEntity(){
        RestTemplate client = restTemplateBuilder.build();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "yue");
        paramMap.add("id", 4);
        ResponseEntity<User> responseEntity = client.postForEntity("http://localhost:8080/postUser", paramMap, User.class);
        System.out.println(responseEntity.getBody().getName());
    }

    @Test
    public void postForObject(){
        RestTemplate client = restTemplateBuilder.build();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "yue");
        paramMap.add("id", 4);
        String response = client.postForObject("http://localhost:8080/postUser", paramMap, String.class);
        System.out.println(response);
    }

    @Test
    public void postForExchange(){
        RestTemplate client = restTemplateBuilder.build();
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "yue");
        paramMap.add("id", 4);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);
        ResponseEntity<String> responseEntity = client.exchange("http://localhost:8080/postUser", HttpMethod.POST, httpEntity, String.class, paramMap);
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity);
    }

    @Test
    public void postForLocation(){
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        paramMap.add("name", "yue");
        paramMap.add("id", 4);
        RestTemplate client = restTemplateBuilder.build();
        URI response = client.postForLocation("http://localhost:8080/post", paramMap);
        System.out.println(response);
    }
}
