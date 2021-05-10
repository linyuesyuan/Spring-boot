package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldMvcController {

    @RequestMapping("/helloWorld")
    public String helloWorld(Model model){
        model.addAttribute("mav","Hello, Spring Boot! 我是MVC架構");
        return "example/hello";
    }

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
