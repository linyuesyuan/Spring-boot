package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    public static final String yue = "asdf";

    @RequestMapping("/hello")
    public String hello(){
        return "Hello, Spring Boot!";
    }

    public static void main(String[] args) {
        System.out.println("asdf");
        System.out.println("HelloWorldController.main");
        for (int i1 = 0; i1 < 1; i1++) {
            if (yue == null) {
                for (int d = 0; d < i1; d++) {

                }
            }
        }
    }

}
