package com.akriskovets.microservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @KafkaListener(id = "myId", topics = "messages")
    public void printMessage(String message) {
        System.out.println(message);
    }
}
