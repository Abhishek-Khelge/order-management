package com.order.management.paymentProvider;

import com.order.management.model.order.OrderRequest;
import org.springframework.stereotype.Service;

@Service
public class RazorPayProvider implements PaymentProvider {
    private int failureCount = 0;

    @Override
    public String generatePaymentRedirectUrl(OrderRequest orderRequest) {
        // Implementation for generating RazorPay redirect URL
        // Include logic to create payment request, interact with RazorPay API, etc.
        // Simulate failure for demonstration purposes
        if (false) {
            failureCount++;
            throw new RuntimeException("RazorPay API failure");
        } else {
            return "https://alternative-provider.com/payment-url";
        }
    }

    @Override
    public String getCurrentProviderName() {
        return "RazorPay";
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
