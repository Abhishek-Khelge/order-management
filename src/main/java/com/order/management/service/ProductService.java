package com.order.management.service;

import com.order.management.db.ProductDetails;
import com.order.management.db.ProductDetailsRepository;
import com.order.management.model.product.PriceQuoteRequest;
import com.order.management.model.product.PriceQuoteResponse;
import com.order.management.service.price.BasePrice;
import com.order.management.service.price.PriceComponent;
import com.order.management.service.price.decorator.DeliveryDecorator;
import com.order.management.service.price.decorator.DiscountDecorator;
import com.order.management.service.price.decorator.PlatformFeeDecorator;
import com.order.management.service.price.decorator.TaxDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    public PriceQuoteResponse calculatePriceQuote(Integer productId, PriceQuoteRequest priceQuoteRequest) {
        ProductDetails productDetails = productDetailsRepository.getProductDetails(productId);

        PriceComponent priceCalculator = new BasePrice();

        BigDecimal basePrice = new BigDecimal("50.0");

        DiscountDecorator discountDecorator = null;
        PlatformFeeDecorator platformFeeDecorator = null;
        DeliveryDecorator deliveryDecorator = null;
        TaxDecorator taxDecorator = null;

        if (priceQuoteRequest.hasCoupon()) {
            discountDecorator = new DiscountDecorator(priceCalculator, priceQuoteRequest.getCouponCode());
            priceCalculator = discountDecorator;
        }

        if (priceQuoteRequest.isIncludePlatformFee()) {
            platformFeeDecorator = new PlatformFeeDecorator(priceCalculator, productDetails.getPrice());
            priceCalculator = platformFeeDecorator;
        }

        if (priceQuoteRequest.applyDelivery()) {
            deliveryDecorator = new DeliveryDecorator(priceCalculator, priceQuoteRequest.getDistance());
            priceCalculator = deliveryDecorator;
        }

        if (priceQuoteRequest.isIncludeTax()) {
            taxDecorator = new TaxDecorator(priceCalculator, productDetails.getCategory());
            priceCalculator = taxDecorator;
        }

        BigDecimal finalPrice = priceCalculator.calculate(basePrice);

        System.out.println("Final Price: $" + finalPrice);

        BigDecimal discount = (discountDecorator != null) ? discountDecorator.getCouponDiscountAmount(basePrice) : BigDecimal.ZERO;
        BigDecimal deliveryCharges = (deliveryDecorator != null) ? deliveryDecorator.calculateDeliveryFee() : BigDecimal.ZERO;
        BigDecimal tax = (taxDecorator != null) ? taxDecorator.calculateTax(basePrice) : BigDecimal.ZERO;
        BigDecimal platformFee = (platformFeeDecorator != null) ? platformFeeDecorator.getPlatformFee(basePrice) : BigDecimal.ZERO;

        return new PriceQuoteResponse(basePrice, discount, tax, platformFee, deliveryCharges, finalPrice);
    }

}
