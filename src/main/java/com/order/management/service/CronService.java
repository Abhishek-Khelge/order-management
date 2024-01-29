package com.order.management.service;

import com.order.management.entity.Order;
import com.order.management.entity.OrderStatus;
import com.order.management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronService {


    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void handlePaymentPendingOrder() {
        List<Order> pendingPaymentOrders = orderRepository.findAllByOrderStatus(OrderStatus.PENDING_PAYMENT);

        pendingPaymentOrders.forEach(order -> {
            order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
        });

        orderRepository.saveAll(pendingPaymentOrders);
    }
}
