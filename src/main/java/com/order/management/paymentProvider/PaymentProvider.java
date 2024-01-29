package com.order.management.paymentProvider;

import com.order.management.model.order.OrderRequest;

public interface PaymentProvider {
    String generatePaymentRedirectUrl(OrderRequest orderRequest);

    String getCurrentProviderName();

    void resetFailureCount();

    int getFailureCount();
}
