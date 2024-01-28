package com.order.management.repository;

import com.order.management.entity.Cart;
import com.order.management.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends BaseJpaRepository<Cart, String> {

    Page<Cart> findAllByUserId(String userId, Pageable pageable);

    Optional<Cart> findByUserIdAndProductId(String userId, String productId);

}
