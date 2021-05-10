package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldMvcController {
    @RequestMapping("/helloworld")
    @ResponseBody
    public String helloWorld() throws Exception{
        return "Hello, Spring Boot!";
    }
}
