package com.order.management.repository;

import com.order.management.entity.OrderDetails;
import com.order.management.entity.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends BaseJpaRepository<OrderDetails, String> {

    List<OrderDetails> findAllByOrderStatus(OrderStatus orderStatus);
}
