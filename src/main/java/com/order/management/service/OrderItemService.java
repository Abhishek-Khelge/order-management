package com.order.management.service;

import com.order.management.entity.Order;
import com.order.management.entity.OrderItem;
import com.order.management.repository.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getOrderItemsForOrderId(String orderId) {
        return orderItemRepository.findAllByOrderId(orderId);
    }

    public OrderItem getOrderByOrderItemId(String orderItemId) {
        return orderItemRepository.findById(orderItemId).orElse(null);
    }
}
