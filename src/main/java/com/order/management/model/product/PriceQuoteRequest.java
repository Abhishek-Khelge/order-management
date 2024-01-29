package com.order.management.model.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceQuoteRequest {
    private String couponCode;
    private boolean includeTax;
    private BigDecimal distance;
    private boolean includePlatformFee;

    public PriceQuoteRequest() {
        this.includeTax = false;
        this.includePlatformFee = false;
    }

    public PriceQuoteRequest(String couponCode, boolean includeTax, BigDecimal distance, boolean includePlatformFee) {
        this.couponCode = couponCode;
        this.includeTax = includeTax;
        this.distance = distance;
        this.includePlatformFee = includePlatformFee;
    }

    public boolean hasCoupon() {
        return this.couponCode != null && !this.couponCode.isEmpty();
    }

    public boolean applyDelivery() {
        return this.distance != null;
    }
}