package com.order.management.paymentProvider;

import com.order.management.model.order.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class PayUProvider implements PaymentProvider {
    private int failureCount = 0;

    @Override
    public String generatePaymentRedirectUrl(OrderRequest orderRequest) {
        // Implementation for generating PayU redirect URL
        // Include logic to create payment request, interact with PayU API, etc.
        // Simulate failure for demonstration purposes
        if (failureCount < 3) {
            failureCount++;
            throw new RuntimeException("PayU API failure");
        } else {
            return "https://alternative-provider.com/payment-url";
        }
    }

    @Override
    public String getCurrentProviderName() {
        return "PayU";
    }

    @Override
    public void resetFailureCount() {
        failureCount = 0;
    }

    @Override
    public int getFailureCount() {
        return failureCount;
    }
}

