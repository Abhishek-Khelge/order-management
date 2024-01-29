package com.order.management.model.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceQuoteResponse {
    private BigDecimal basePrice;
    private BigDecimal discount;
    private BigDecimal tax;
    private BigDecimal platformFee;
    private BigDecimal deliveryCharges;
    private BigDecimal finalPrice;


    public PriceQuoteResponse(BigDecimal basePrice, BigDecimal discount, BigDecimal tax, BigDecimal platformFee, BigDecimal deliveryCharges, BigDecimal finalPrice) {
        this.basePrice = basePrice;
        this.discount = discount;
        this.tax = tax;
        this.platformFee = platformFee;
        this.deliveryCharges = deliveryCharges;
        this.finalPrice = finalPrice;
    }

}