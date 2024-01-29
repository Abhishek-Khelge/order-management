package com.order.management.service.order;

import com.order.management.entity.Order;
import com.order.management.entity.OrderStatus;
import com.order.management.model.order.OrderRequest;
import com.order.management.paymentProvider.PaymentProvider;
import com.order.management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentSwitchingService paymentSwitchingService;
    @Autowired
    private OrderRepository orderRepository;

    public String makePayment(OrderRequest orderRequest) {
        PaymentProvider currentProvider = paymentSwitchingService.getCurrentPaymentProvider();

        try {
            String paymentRedirectUrl = currentProvider.generatePaymentRedirectUrl(orderRequest);
            // If payment succeeds, reset the failure count
            currentProvider.resetFailureCount();
            return "Redirect to: " + paymentRedirectUrl;
        } catch (RuntimeException e) {
            paymentSwitchingService.switchProvider();
            // If payment fails, handle the exception
            System.out.println("Payment failed. Switching to the alternative provider.");
        }

        return "Payment failed. Please try later.";
    }

    // Callback method called on payment success or failure
    public void handlePaymentCallback(String orderId, boolean paymentSuccess) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getOrderStatus() == OrderStatus.PENDING_PAYMENT) {
            if (paymentSuccess) {
                order.setOrderStatus(OrderStatus.PAID);
                // Additional logic for successful payment
            } else {
                order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
                // Additional logic for payment failure
            }
            orderRepository.save(order);
//            notificationService.notifyOrderStatus(order);
        }
    }

}

