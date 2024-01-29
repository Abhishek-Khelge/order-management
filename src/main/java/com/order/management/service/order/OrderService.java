package com.order.management.service.order;

import com.order.management.db.ProductDetails;
import com.order.management.db.ProductDetailsRepository;
import com.order.management.entity.Order;
import com.order.management.entity.OrderStatus;
import com.order.management.model.order.OrderRequest;
import com.order.management.repository.OrderRepository;
import com.order.management.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository orderRepository;

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

            Order order = createOrderEntity(orderRequest, productDetails, userId);

            String paymentRedirectUrl = paymentService.makePayment(orderRequest);

            updateOrderStatus(order, OrderStatus.INITIATED);

            return paymentRedirectUrl;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return "Order placement failed. Please try again later.";
        }
    }

    private Order createOrderEntity(OrderRequest orderRequest, ProductDetails productDetails, String userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(orderRequest.getProductId());
        order.setOrderPrice(productDetails.getPrice().multiply(BigDecimal.valueOf(orderRequest.getQuantity())));
        order.setOrderStatus(OrderStatus.INITIATED);
        order = orderRepository.save(order);
        return order;
    }

    private void updateOrderStatus(Order order, OrderStatus status) {
        order.setOrderStatus(status);
        orderRepository.save(order);
    }

}
