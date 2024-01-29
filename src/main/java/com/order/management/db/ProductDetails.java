package com.order.management.db;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;

@Data
@Builder
public class ProductDetails {
    private String productId;
    private String productName;
    private String productType;
    private String category;
    private String author;
    private BigDecimal price;
    private int quantity;
    private String manufacturer;
    private Map<Object, Object> details;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}