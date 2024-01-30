package com.order.management.model.order;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {
    @NotNull
    @Min(1)
    private Integer productId;
    @NotNull
    @Min(1)
    private Integer quantity;
    private String customerName;
    private String shippingAddress;
}