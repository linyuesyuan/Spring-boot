package com.example.demo.controller;

import com.example.demo.exception.BusinessException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
public class TestController {
    @RequestMapping("BusinessException")
    public String testResponseStatusExceptionResolver(@RequestParam("i") int i){
        if(i==0){
            throw new BusinessException("自訂業務錯誤", 600);
        }
        throw new ValidationException();
    }
}
