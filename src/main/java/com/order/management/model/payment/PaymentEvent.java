package com.order.management.model.payment;

public record PaymentEvent(String orderId, boolean success) {
}
