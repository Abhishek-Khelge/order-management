package com.order.management.service.price;

import java.math.BigDecimal;

public interface PriceComponent {
    BigDecimal calculate(BigDecimal basePrice);
}