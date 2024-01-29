package com.order.management.service.price.decorator;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;

public class PlatformFeeDecorator implements PriceComponent {
    private static final BigDecimal FEE = new BigDecimal("2.0");
    private static final BigDecimal FEE_THRESHOLD = new BigDecimal("100.0");

    private final BigDecimal basePrice;

    private final PriceComponent component;

    public PlatformFeeDecorator(PriceComponent component, BigDecimal basePrice) {
        this.component = component;
        this.basePrice = basePrice;
    }

    @Override
    public BigDecimal calculate(BigDecimal basePrice) {
        BigDecimal calculatedPrice = component.calculate(basePrice);
        BigDecimal platformFee = getPlatformFee(basePrice);
        return calculatedPrice.add(platformFee);
    }

    public BigDecimal getPlatformFee(BigDecimal basePrice) {
        if (basePrice.compareTo(FEE_THRESHOLD) <= 0) {
            return FEE;
        } else {
            return BigDecimal.ZERO; // Do not apply platform fee if the threshold is exceeded
        }
    }
}

