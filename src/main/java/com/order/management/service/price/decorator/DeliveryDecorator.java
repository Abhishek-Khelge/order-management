package com.order.management.service.price.decorator;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;

public class DeliveryDecorator extends PriceDecorator {
    private static final BigDecimal BASE_FEE = new BigDecimal("5.0");
    private static final BigDecimal BASE_DISTANCE = new BigDecimal("5.0");
    private static final BigDecimal ADDITIONAL_FEE_PER_KM = new BigDecimal("1.0");

    private final BigDecimal additionalFeePerKm;
    private final BigDecimal distance;
    final PriceComponent component;

    public DeliveryDecorator(PriceComponent component, BigDecimal distance) {
        super(component);
        this.component = component;
        this.additionalFeePerKm = ADDITIONAL_FEE_PER_KM;
        this.distance = distance;
    }

    @Override
    public BigDecimal calculate(BigDecimal basePrice) {
        BigDecimal calculatedPrice = component.calculate(basePrice);

        BigDecimal deliveryFee = calculateDeliveryFee();

        return calculatedPrice.add(deliveryFee);
    }

    public BigDecimal calculateDeliveryFee() {
        BigDecimal additionalFee = calculateAdditionalFee();
        return BASE_FEE.add(additionalFee);
    }

    private BigDecimal calculateAdditionalFee() {
        // Calculate additional fee based on the remaining distance beyond the base distance
        return distance.subtract(BASE_DISTANCE).max(BigDecimal.ZERO).multiply(additionalFeePerKm);
    }
}


