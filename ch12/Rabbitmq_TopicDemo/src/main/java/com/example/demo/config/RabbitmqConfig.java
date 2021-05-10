package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue queueMessage(){
        return new Queue("topic.a");
    }
    @Bean
    public Queue queueMessages(){
        return new Queue("topic.b");
    }
    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.a");
    }
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }
}
