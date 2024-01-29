package com.order.management.controller;

import com.order.management.model.payment.PaymentEvent;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.order.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/api/callback/payment")
    public ResponseEntity<ResponseModel> handlePaymentCallback(@RequestBody PaymentEvent paymentEvent) {
        System.out.println("Received callback for payment event: " + paymentEvent);
        paymentService.handlePaymentCallback(paymentEvent.orderId(), paymentEvent.success());
        return ResponseModel.getInstance("status", "Success", HttpStatus.OK);
    }
}
