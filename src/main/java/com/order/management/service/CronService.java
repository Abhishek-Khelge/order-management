package com.order.management.service;

import com.order.management.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CronService {


    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

//    @Scheduled(cron = "0 0/2 * * * ?")
//    public void handlePaymentPendingOrder() {
//        List<Order> pendingPaymentOrders = orderRepository.findAllByOrderStatus(OrderStatus.PAYMENT_PENDING);
//
//        pendingPaymentOrders.forEach(order -> {
//            order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
//        });
//
//        orderRepository.saveAll(pendingPaymentOrders);
//    }
}
