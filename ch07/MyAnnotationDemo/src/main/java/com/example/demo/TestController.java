package com.example.demo;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAspectJAutoProxy
public class TestController {
    @RequestMapping("/")
    @MyTestAnnotation("測試Annotation參數")
    public void testAnnotation(){
        System.err.println("測試自定義註釋");
    }
}
