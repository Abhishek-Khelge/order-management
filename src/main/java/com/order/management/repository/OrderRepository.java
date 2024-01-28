package com.order.management.repository;

import com.order.management.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseJpaRepository<Order, String> {

    Page<Order> findAllByUserId(String userId, Pageable pageable);
}
