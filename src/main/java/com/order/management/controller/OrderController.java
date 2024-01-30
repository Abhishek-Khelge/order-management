package com.order.management.controller;

import com.order.management.model.order.OrderRequest;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping("/v1/order")
    public ResponseEntity<ResponseModel> placeOrder(
            @RequestParam("user_id") String userId,
            @RequestBody OrderRequest orderRequest) {
        String reDirectionOrder = orderService.placeOrder(userId, orderRequest);
        return ResponseModel.getInstance("order_response", reDirectionOrder, HttpStatus.SEE_OTHER);
    }
}
