package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
public class MVCDemoController {
    @GetMapping("/mvcdemo")
    public ModelAndView hello(){
        User user = new User();
        user.setName("yue");
        user.setAge(24);

        ModelAndView modelAndView = new ModelAndView("mvcdemo");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
