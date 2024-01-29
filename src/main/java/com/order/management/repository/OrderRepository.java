package com.order.management.repository;

import com.order.management.entity.Order;
import com.order.management.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends BaseJpaRepository<Order, String> {

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
}
