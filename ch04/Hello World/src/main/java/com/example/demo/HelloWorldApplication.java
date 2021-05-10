package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(HelloWorldApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
//		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
