package com.order.management.service.price.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DiscountDecoratorTest {

    @Test
    void testConstructor() {
        PriceComponent component = mock(PriceComponent.class);
        BigDecimal distance = new BigDecimal("2.3");
        DeliveryDecorator actualDeliveryDecorator = new DeliveryDecorator(component, distance);

        assertEquals(new BigDecimal("2.3"), distance);
        assertSame(actualDeliveryDecorator.component, component);
    }

    @Test
    void testCalculate() {
        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(new BigDecimal("2.3"));
        DeliveryDecorator deliveryDecorator = new DeliveryDecorator(component, new BigDecimal("2.3"));
        BigDecimal actualCalculateResult = deliveryDecorator.calculate(new BigDecimal("2.3"));
        verify(component).calculate(Mockito.<BigDecimal>any());
        assertEquals(new BigDecimal("7.3"), actualCalculateResult);
    }

    @Test
    void testCalculateDeliveryFee() {
        PriceComponent component = mock(PriceComponent.class);
        BigDecimal actualCalculateDeliveryFeeResult = (new DeliveryDecorator(component, new BigDecimal("2.3")))
                .calculateDeliveryFee();
        assertEquals(new BigDecimal("5.0"), actualCalculateDeliveryFeeResult);
    }

}
