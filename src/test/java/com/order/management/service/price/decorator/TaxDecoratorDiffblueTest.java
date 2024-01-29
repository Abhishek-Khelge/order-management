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

class TaxDecoratorDiffblueTest {
    /**
     * Method under test: {@link TaxDecorator#TaxDecorator(PriceComponent, String)}
     */
    @Test
    void testConstructor() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TaxDecorator.component
        //     TaxDecorator.taxRate

        new TaxDecorator(mock(PriceComponent.class), "Category");

    }

    /**
     * Method under test: {@link TaxDecorator#calculate(BigDecimal)}
     */
    @Test
    void testCalculate() {
        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(new BigDecimal("2.3"));
        TaxDecorator taxDecorator = new TaxDecorator(component, "Category");
        BigDecimal actualCalculateResult = taxDecorator.calculate(new BigDecimal("2.3"));
        verify(component).calculate(Mockito.<BigDecimal>any());
        assertEquals(new BigDecimal("2.576"), actualCalculateResult);
    }

    /**
     * Method under test: {@link TaxDecorator#calculate(BigDecimal)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.add(java.math.BigDecimal)" because "calculatedPrice" is null
        //       at com.order.management.service.price.decorator.TaxDecorator.calculate(TaxDecorator.java:31)
        //   See https://diff.blue/R013 to resolve this issue.

        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(null);
        TaxDecorator taxDecorator = new TaxDecorator(component, "Category");
        taxDecorator.calculate(new BigDecimal("2.3"));
    }

    /**
     * Method under test: {@link TaxDecorator#calculate(BigDecimal)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculate3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.multiply(java.math.BigDecimal)" because "basePrice" is null
        //       at com.order.management.service.price.decorator.TaxDecorator.calculateTax(TaxDecorator.java:35)
        //       at com.order.management.service.price.decorator.TaxDecorator.calculate(TaxDecorator.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        PriceComponent component = mock(PriceComponent.class);
        when(component.calculate(Mockito.<BigDecimal>any())).thenReturn(new BigDecimal("2.3"));
        (new TaxDecorator(component, "Category")).calculate(null);
    }

    /**
     * Method under test: {@link TaxDecorator#calculateTax(BigDecimal)}
     */
    @Test
    void testCalculateTax() {
        TaxDecorator taxDecorator = new TaxDecorator(mock(PriceComponent.class), "Category");
        BigDecimal actualCalculateTaxResult = taxDecorator.calculateTax(new BigDecimal("2.3"));
        assertEquals(new BigDecimal("0.276"), actualCalculateTaxResult);
    }

    /**
     * Method under test: {@link TaxDecorator#calculateTax(BigDecimal)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCalculateTax2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.math.BigDecimal.multiply(java.math.BigDecimal)" because "basePrice" is null
        //       at com.order.management.service.price.decorator.TaxDecorator.calculateTax(TaxDecorator.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        (new TaxDecorator(mock(PriceComponent.class), "Category")).calculateTax(null);
    }
}
