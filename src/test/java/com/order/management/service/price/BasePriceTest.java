package com.order.management.service.price;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasePriceTest {

    @Autowired
    private BasePrice basePrice;

    @Test
    public void calculate_shouldReturnSamePrice() {
        BigDecimal initialPrice = new BigDecimal("100.00");
        BigDecimal calculatedPrice = basePrice.calculate(initialPrice);

        assertEquals(initialPrice, calculatedPrice);
    }
}
