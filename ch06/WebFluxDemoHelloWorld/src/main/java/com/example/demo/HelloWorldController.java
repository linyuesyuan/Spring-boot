package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorldController {
    @GetMapping("/helloWorld")
    public Mono<String> helloWorld(){
        return Mono.just("This is WebFlux demo.");
    }
}
