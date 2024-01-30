package com.order.management.model.order;


import lombok.Data;

@Data
public class OrderRequest {
    private Integer productId;
    private Integer quantity;
    private String customerName;
    private String shippingAddress;
}