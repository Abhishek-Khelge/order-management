package com.order.management.service;

import com.order.management.model.NotificationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void addEvent(String orderId) {
        kafkaTemplate.send("notification-topic", orderId);
    }

}
