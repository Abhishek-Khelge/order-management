package com.order.management.service.price;

import java.math.BigDecimal;

public class BasePrice implements PriceComponent {
    @Override
    public BigDecimal calculate(BigDecimal basePrice) {
        return basePrice;
    }
}