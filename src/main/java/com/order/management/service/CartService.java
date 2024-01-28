package com.order.management.service;

import com.order.management.entity.Cart;
import com.order.management.repository.CartRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUserIdAndProductId(String userId, String productId) {
        Optional<Cart> cart = cartRepository.findByUserIdAndProductId(userId, productId);
        return cart.orElse(null);
    }

    @SneakyThrows
    public Cart addCart(Cart cart) {
        try {
            cart = cartRepository.save(cart);
        } catch (DataIntegrityViolationException ex) {
            // todo: condition
//            if (ex.getCause() instanceof ConstraintViolationException constraintViolationException) {
//                if ("inventory_serial_number_key".equals(constraintViolationException.getConstraintName())) {
//                    throw new OrderManagementException(OrderManagementError.PRODUCT_ALREADY_EXISTS, new String[]{inventory.getSerialNumber()});
//                }
//            }
        }
        return cart;
    }

    public void deleteCart(String cartId) {
        cartRepository.deleteById(cartId);
    }

}
