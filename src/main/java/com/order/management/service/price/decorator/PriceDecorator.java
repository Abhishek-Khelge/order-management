package com.order.management.service.price.decorator;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;

public abstract class PriceDecorator implements PriceComponent {
    protected PriceComponent component;

    public PriceDecorator(PriceComponent component) {
        this.component = component;
    }

    @Override
    public abstract BigDecimal calculate(BigDecimal basePrice);
}
