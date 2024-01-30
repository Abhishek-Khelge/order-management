package com.order.management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void addEvent(String orderId) {
        log.info("added event for order id {}, in kafka", orderId);
        kafkaTemplate.send("notification-topic", orderId);
    }

}
