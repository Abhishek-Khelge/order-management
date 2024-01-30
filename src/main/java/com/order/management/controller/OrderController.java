package com.order.management.controller;

import com.order.management.model.order.OrderRequest;
import com.order.management.model.response.Message;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.order.OrderService;
import com.order.management.service.validator.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private OrderValidator orderValidator;

    @PostMapping("/v1/order")
    public ResponseEntity<ResponseModel> placeOrder(
            @RequestParam("user_id") String userId,
            @RequestBody OrderRequest orderRequest) {
        List<Message> messages = orderValidator.validate(orderRequest);
        if (!messages.isEmpty()) {
            return ResponseModel.getInstance(messages, HttpStatus.BAD_REQUEST);
        }
        String reDirectionOrder = orderService.placeOrder(userId, orderRequest);
        return ResponseModel.getInstance("order_response", reDirectionOrder, HttpStatus.SEE_OTHER);
    }
}
