package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TestValidator {

    @GetMapping("/test")
    public String showForm(User user){
        return "form";
    }

    @GetMapping("/results")
    public String results(){
        return "results";
    }

    @PostMapping("/test")
    public String checkUser(@Valid User user, BindingResult bindingResult, RedirectAttributes attributes){
        if(bindingResult.hasErrors()){
            System.out.println("user has errors.");
            System.out.println(user.getName());
            System.out.println(user.getAge());
            System.out.println(user.getEmail());
            System.out.println(user.getAnswer());
            return "form";
        }
        attributes.addFlashAttribute("user", user);
        return "redirect:/results";
    }
}
