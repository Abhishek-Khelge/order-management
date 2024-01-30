package com.order.management.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "notification-topic", groupId = "my-group")
    public void consume(String orderId) {
        System.out.println("Received notification: " + orderId);
        // Additional processing logic can be added if needed
    }

}
