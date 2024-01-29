package com.order.management.service.price.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.order.management.service.price.PriceComponent;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeliveryDecoratorDiffblueTest {
    /**
     * Method under test:
     * {@link DeliveryDecorator#DeliveryDecorator(PriceComponent, BigDecimal)}
     */
    @Test
    void testConstructor() {
        PriceComponent component = mock(PriceComponent.class);
        DeliveryDecorator actualDeliveryDecorator = new DeliveryDecorator(component, new BigDecimal("2.3"));

        BigDecimal expectedCalculateDeliveryFeeResult = new BigDecimal("5.0");
        assertEquals(expectedCalculateDeliveryFeeResult, actualDeliveryDecorator.calculateDeliveryFee());
    }

    /**
     * Method under test: {@link DeliveryDecorator#calculate(BigDecimal)}
     */
    @Test
    void testCalculate() {
        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(new BigDecimal("2.3"));
        DeliveryDecorator deliveryDecorator = new DeliveryDecorator(component, new BigDecimal("2.3"));
        BigDecimal actualCalculateResult = deliveryDecorator.calculate(new BigDecimal("2.3"));
        verify(component).calculate(Mockito.<BigDecimal>any());
        assertEquals(new BigDecimal("7.3"), actualCalculateResult);
    }

    /**
     * Method under test: {@link DeliveryDecorator#calculate(BigDecimal)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.add(java.math.BigDecimal)" because "calculatedPrice" is null
        //       at com.order.management.service.price.decorator.DeliveryDecorator.calculate(DeliveryDecorator.java:28)
        //   See https://diff.blue/R013 to resolve this issue.

        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(null);
        DeliveryDecorator deliveryDecorator = new DeliveryDecorator(component, new BigDecimal("2.3"));
        deliveryDecorator.calculate(new BigDecimal("2.3"));
    }

    /**
     * Method under test: {@link DeliveryDecorator#calculate(BigDecimal)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculate3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.subtract(java.math.BigDecimal)" because "this.distance" is null
        //       at com.order.management.service.price.decorator.DeliveryDecorator.calculateAdditionalFee(DeliveryDecorator.java:38)
        //       at com.order.management.service.price.decorator.DeliveryDecorator.calculateDeliveryFee(DeliveryDecorator.java:32)
        //       at com.order.management.service.price.decorator.DeliveryDecorator.calculate(DeliveryDecorator.java:26)
        //   See https://diff.blue/R013 to resolve this issue.

        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(new BigDecimal("2.3"));
        DeliveryDecorator deliveryDecorator = new DeliveryDecorator(component, null);
        deliveryDecorator.calculate(new BigDecimal("2.3"));
    }

    /**
     * Method under test: {@link DeliveryDecorator#calculateDeliveryFee()}
     */
    @Test
    void testCalculateDeliveryFee() {
        PriceComponent component = mock(PriceComponent.class);
        BigDecimal actualCalculateDeliveryFeeResult = (new DeliveryDecorator(component, new BigDecimal("2.3")))
                .calculateDeliveryFee();
        assertEquals(new BigDecimal("5.0"), actualCalculateDeliveryFeeResult);
    }

    /**
     * Method under test: {@link DeliveryDecorator#calculateDeliveryFee()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateDeliveryFee2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.subtract(java.math.BigDecimal)" because "this.distance" is null
        //       at com.order.management.service.price.decorator.DeliveryDecorator.calculateAdditionalFee(DeliveryDecorator.java:38)
        //       at com.order.management.service.price.decorator.DeliveryDecorator.calculateDeliveryFee(DeliveryDecorator.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        (new DeliveryDecorator(mock(PriceComponent.class), null)).calculateDeliveryFee();
    }
}
