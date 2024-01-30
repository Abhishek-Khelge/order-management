package com.order.management.service.order;

import com.order.management.db.ProductDetails;
import com.order.management.db.ProductDetailsRepository;
import com.order.management.entity.OrderDetails;
import com.order.management.entity.OrderStatus;
import com.order.management.exception.OrderManagementError;
import com.order.management.exception.OrderManagementException;
import com.order.management.model.order.OrderRequest;
import com.order.management.repository.OrderDetailsRepository;
import com.order.management.service.NotificationService;
import lombok.SneakyThrows;
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


    @SneakyThrows
    public String placeOrder(String userId, OrderRequest orderRequest) {
        ProductDetails productDetails = productDetailsRepository.getProductDetails(orderRequest.getProductId());
        if (productDetails == null) {
            throw new OrderManagementException(OrderManagementError.PRODUCT_DOES_NOT_EXISTS, new String[]{Integer.toString(orderRequest.getProductId())});
        }
        if (productDetails.getQuantity() < orderRequest.getQuantity()) {
            throw new OrderManagementException(OrderManagementError.PRODUCT_QUANTITY_EXCEEDED, null);
        }
        try {
            OrderDetails orderDetails = createOrderEntity(orderRequest, productDetails, userId);
            String paymentRedirectUrl = paymentService.makePayment(orderRequest);
            updateOrderStatus(orderDetails, OrderStatus.PAYMENT_PENDING);
            return paymentRedirectUrl;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            throw new OrderManagementException(OrderManagementError.ORDER_FAILED, null);
        }
    }

    private OrderDetails createOrderEntity(OrderRequest orderRequest, ProductDetails productDetails, String userId) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setUserId(userId);
        orderDetails.setProductId(orderRequest.getProductId());
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
