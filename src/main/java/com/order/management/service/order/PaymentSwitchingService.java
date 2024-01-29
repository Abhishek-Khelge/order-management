package com.order.management.service.order;

import com.order.management.paymentProvider.PayUProvider;
import com.order.management.paymentProvider.PaymentProvider;
import com.order.management.paymentProvider.RazorPayProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentSwitchingService {

    private final RazorPayProvider razorPayProvider;
    private final PayUProvider payUProvider;

    private String currentProvider = "RazorPay";

    @Autowired
    public PaymentSwitchingService(RazorPayProvider razorPayProvider, PayUProvider payUProvider) {
        this.razorPayProvider = razorPayProvider;
        this.payUProvider = payUProvider;
    }

    public PaymentProvider getCurrentPaymentProvider() {
        if (currentProvider.equals("RazorPay")) {
            return razorPayProvider;
        } else if (currentProvider.equals("PayU")) {
            return payUProvider;
        }
        throw new RuntimeException();
    }

    public void switchProvider() {
        if (currentProvider.equals("RazorPay")) {
            currentProvider = "PayU";
        } else if (currentProvider.equals("PayU")) {
            currentProvider = null;
        }
    }
}

