package com.order.management.repository;

import com.order.management.entity.OrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends BaseJpaRepository<OrderItem, String> {

    List<OrderItem> findAllByOrderId(String orderId);
}
