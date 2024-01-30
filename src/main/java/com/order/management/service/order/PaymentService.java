package com.order.management.service.order;

import com.order.management.entity.OrderDetails;
import com.order.management.entity.OrderStatus;
import com.order.management.model.NotificationModel;
import com.order.management.model.order.OrderRequest;
import com.order.management.paymentProvider.PaymentProvider;
import com.order.management.repository.OrderDetailsRepository;
import com.order.management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentSwitchingService paymentSwitchingService;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private NotificationService notificationService;

    public String makePayment(OrderRequest orderRequest) {
        PaymentProvider currentProvider = paymentSwitchingService.getCurrentPaymentProvider();

        try {
            String paymentRedirectUrl = currentProvider.generatePaymentRedirectUrl(orderRequest);
            // If payment succeeds, reset the failure count
            currentProvider.resetFailureCount();
            return "Redirect to: " + paymentRedirectUrl;
        } catch (RuntimeException e) {
            System.out.println(paymentSwitchingService.getCurrentPaymentProvider());
            if (currentProvider.getFailureCount() > 1){
                paymentSwitchingService.switchProvider();
            }
            // If payment fails, handle the exception
            System.out.println("Payment failed. Switching to the alternative provider.");
        }

        return "Payment failed. Please try later.";
    }

    // Callback method called on payment success or failure
    public void handlePaymentCallback(String orderId, boolean paymentSuccess) {
        OrderDetails order = orderDetailsRepository.findById(orderId).orElse(null);
        if (order != null && order.getOrderStatus() == OrderStatus.PAYMENT_PENDING) {
            if (paymentSuccess) {
                order.setOrderStatus(OrderStatus.PAID);
            } else {
                order.setOrderStatus(OrderStatus.PAYMENT_FAILED);
            }
            orderDetailsRepository.save(order);
//            notificationService.addEvent(new NotificationModel(orderId, order.getOrderStatus()));
        }
        notificationService.addEvent(orderId);
    }

}

