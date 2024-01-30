package com.order.management.service.order;

import com.order.management.db.ProductDetails;
import com.order.management.db.ProductDetailsRepository;
import com.order.management.entity.OrderDetails;
import com.order.management.entity.OrderStatus;
import com.order.management.model.order.OrderRequest;
import com.order.management.repository.OrderDetailsRepository;
import com.order.management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;


    public String placeOrder(String userId, OrderRequest orderRequest) {
        try {
            ProductDetails productDetails = productDetailsRepository.getProductDetails(orderRequest.getProductId());
            if (productDetails == null || productDetails.getQuantity() < orderRequest.getQuantity()) {
                return "Product not available or quantity exceeded.";
            }

            OrderDetails orderDetails = createOrderEntity(orderRequest, productDetails, userId);

            String paymentRedirectUrl = paymentService.makePayment(orderRequest);

            updateOrderStatus(orderDetails, OrderStatus.PAYMENT_PENDING);

            return paymentRedirectUrl;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return "Order placement failed. Please try again later.";
        }
    }

    private OrderDetails createOrderEntity(OrderRequest orderRequest, ProductDetails productDetails, String userId) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUserId(userId);
        orderDetails.setProductId(orderDetails.getProductId());
        orderDetails.setOrderPrice(productDetails.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity())));
        orderDetails.setOrderStatus(OrderStatus.INITIATED);
        orderDetails = orderDetailsRepository.save(orderDetails);
        return orderDetails;
    }

    private void updateOrderStatus(OrderDetails orderDetails, OrderStatus status) {
        orderDetails.setOrderStatus(status);
        orderDetailsRepository.save(orderDetails);
    }

}
