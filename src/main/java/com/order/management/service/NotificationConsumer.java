package com.order.management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    @KafkaListener(topics = "notification-topic", groupId = "my-group")
    public void consume(String orderId) {
        log.info("Received notification: " + orderId);
    }

}
