package com.order.management.service.price.decorator;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DiscountDecorator extends PriceDecorator {
    private static final Map<String, BigDecimal> COUPON_DISCOUNT_MAP;

    static {
        COUPON_DISCOUNT_MAP = new HashMap<>();
        COUPON_DISCOUNT_MAP.put("SAVE10", new BigDecimal("10")); // 10$
        COUPON_DISCOUNT_MAP.put("FREESHIP", new BigDecimal("5")); // 5$
        // Add more coupons and discount amounts as needed
    }

    private BigDecimal discountAmount;
    private final PriceComponent component;

    public DiscountDecorator(PriceComponent component, String couponCode) {
        super(component);
        this.component = component;
        this.discountAmount = getCouponDiscountAmount(couponCode);
    }

    @Override
    public BigDecimal calculate(BigDecimal basePrice) {
        BigDecimal calculatedPrice = component.calculate(basePrice);
        BigDecimal discountPrice = getCouponDiscountAmount();
        discountPrice = discountPrice.max(BigDecimal.ZERO);
        return calculatedPrice.subtract(discountPrice);
    }

    public BigDecimal getCouponDiscountAmount() {
        return discountAmount;
    }

    private BigDecimal getCouponDiscountAmount(String couponCode) {
        return COUPON_DISCOUNT_MAP.getOrDefault(couponCode, BigDecimal.ZERO);
    }

    public void setCouponCode(String couponCode) {
        this.discountAmount = getCouponDiscountAmount(couponCode);
    }
}

