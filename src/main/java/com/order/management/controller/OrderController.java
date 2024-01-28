package com.order.management.controller;

import com.order.management.model.response.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class OrderController {

    @GetMapping("/v1/order")
    public ResponseModel getAllOrders() {
        return null;
    }

    @GetMapping("/v1/order/{order_id}")
    public ResponseModel getOrderDetailsById() {
        return null;
    }

    @PostMapping("/v1/order/place")
    public ResponseModel placeOrder() {
        return null;
    }

    @PutMapping("/v1/order/detail/{order_id}")
    public ResponseModel updateOrderDetails() {
        return null;
    }

    @PutMapping("/v1/order/cancel")
    public ResponseModel cancelOrder() {
        return null;
    }

    @DeleteMapping("/v1/order/product")
    public ResponseModel deleteProduct() {
        return null;
    }
}
