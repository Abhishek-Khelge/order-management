package com.order.management.service.price.decorator;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TaxDecorator implements PriceComponent {
    private static final Map<String, BigDecimal> CATEGORY_TAX_MAP;

    static {
        CATEGORY_TAX_MAP = new HashMap<>();
        CATEGORY_TAX_MAP.put("electronics", new BigDecimal("0.08"));
        CATEGORY_TAX_MAP.put("clothing", new BigDecimal("0.05"));
        // Add more categories and tax rates as needed
    }

    private final BigDecimal taxRate;
    private final PriceComponent component;

    public TaxDecorator(PriceComponent component, String category) {
        this.component = component;
        this.taxRate = getCategoryTaxRate(category);
    }

    @Override
    public BigDecimal calculate(BigDecimal basePrice) {
        BigDecimal calculatedPrice = component.calculate(basePrice);
        BigDecimal tax = calculateTax(basePrice);
        return calculatedPrice.add(tax);
    }

    public BigDecimal calculateTax(BigDecimal basePrice) {
        return basePrice.multiply(taxRate);
    }

    private BigDecimal getCategoryTaxRate(String category) {
        return CATEGORY_TAX_MAP.getOrDefault(category, new BigDecimal("0.12"));
    }
}