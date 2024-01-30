package com.order.management.service;

import com.order.management.db.ProductDetails;
import com.order.management.db.ProductDetailsRepository;
import com.order.management.exception.OrderManagementError;
import com.order.management.exception.OrderManagementException;
import com.order.management.model.product.PriceQuoteRequest;
import com.order.management.model.product.PriceQuoteResponse;
import com.order.management.service.price.BasePrice;
import com.order.management.service.price.PriceComponent;
import com.order.management.service.price.decorator.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @SneakyThrows
    public PriceQuoteResponse calculatePriceQuote(Integer productId, PriceQuoteRequest priceQuoteRequest) {
        try {
            log.info("calculating price quote for product id :{}", productId);
            ProductDetails productDetails = productDetailsRepository.getProductDetails(productId);

            List<PriceDecorator> decorators = new ArrayList<>();
            PriceComponent priceCalculator = initializePriceCalculator(productDetails, decorators, priceQuoteRequest);

            BigDecimal basePrice = productDetails.getPrice();
            BigDecimal finalPrice = priceCalculator.calculate(basePrice);

            BigDecimal discount = getDiscountAmount(decorators, basePrice);
            BigDecimal deliveryCharges = calculateDeliveryCharges(decorators);
            BigDecimal tax = calculateTax(decorators, basePrice);
            BigDecimal platformFee = calculatePlatformFee(decorators, basePrice);
            log.info("calculated finalPrice quote for product id :{} is : {}", productId, finalPrice);
            return new PriceQuoteResponse(basePrice, discount, tax, platformFee, deliveryCharges, finalPrice);
        } catch (Exception e) {
            log.error("Error calculating price quote for product id: {}", productId, e);
            throw new OrderManagementException(OrderManagementError.PRICE_COMPUTE_FAILED, null);
        }
    }

    private PriceComponent initializePriceCalculator(ProductDetails productDetails, List<PriceDecorator> decorators, PriceQuoteRequest priceQuoteRequest) {
        PriceComponent priceCalculator = new BasePrice();
        if (priceQuoteRequest.applyDelivery()) {
            priceCalculator = initializeWithDeliveryDecorator(decorators, priceCalculator, priceQuoteRequest.getDistance());
        }
        if (priceQuoteRequest.hasCoupon()) {
            priceCalculator = initializeWithDiscountDecorator(decorators, priceCalculator, priceQuoteRequest);
        }
        if (priceQuoteRequest.isIncludePlatformFee()) {
            priceCalculator = initializeWithPlatformFeeDecorator(decorators, priceCalculator, productDetails.getPrice());
        }
        if (priceQuoteRequest.isIncludeTax()) {
            priceCalculator = initializeWithTaxDecorator(decorators, priceCalculator, productDetails.getCategory());
        }
        return priceCalculator;
    }

    private PriceComponent initializeWithDiscountDecorator(List<PriceDecorator> decorators, PriceComponent priceCalculator, PriceQuoteRequest priceQuoteRequest) {
        DiscountDecorator discountDecorator = new DiscountDecorator(priceCalculator, priceQuoteRequest.getCouponCode());
        decorators.add(discountDecorator);
        return discountDecorator;
    }

    private PriceComponent initializeWithPlatformFeeDecorator(List<PriceDecorator> decorators, PriceComponent priceCalculator, BigDecimal productPrice) {
        PlatformFeeDecorator platformFeeDecorator = new PlatformFeeDecorator(priceCalculator, productPrice);
        decorators.add(platformFeeDecorator);
        return platformFeeDecorator;
    }

    private PriceComponent initializeWithDeliveryDecorator(List<PriceDecorator> decorators, PriceComponent priceCalculator, BigDecimal distance) {
        DeliveryDecorator deliveryDecorator = new DeliveryDecorator(priceCalculator, distance);
        decorators.add(deliveryDecorator);
        return deliveryDecorator;
    }

    private PriceComponent initializeWithTaxDecorator(List<PriceDecorator> decorators, PriceComponent priceCalculator, String category) {
        TaxDecorator taxDecorator = new TaxDecorator(priceCalculator, category);
        decorators.add(taxDecorator);
        return taxDecorator;
    }

    private BigDecimal getDiscountAmount(List<PriceDecorator> decorators, BigDecimal basePrice) {
        return decorators.stream()
                .filter(decorator -> decorator instanceof DiscountDecorator)
                .map(decorator -> ((DiscountDecorator) decorator).getCouponDiscountAmount(basePrice))
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateDeliveryCharges(List<PriceDecorator> decorators) {
        return decorators.stream()
                .filter(decorator -> decorator instanceof DeliveryDecorator)
                .map(decorator -> ((DeliveryDecorator) decorator).calculateDeliveryFee())
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateTax(List<PriceDecorator> decorators, BigDecimal basePrice) {
        return decorators.stream()
                .filter(decorator -> decorator instanceof TaxDecorator)
                .map(decorator -> ((TaxDecorator) decorator).calculateTax(basePrice))
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculatePlatformFee(List<PriceDecorator> decorators, BigDecimal basePrice) {
        return decorators.stream()
                .filter(decorator -> decorator instanceof PlatformFeeDecorator)
                .map(decorator -> ((PlatformFeeDecorator) decorator).getPlatformFee(basePrice))
                .findFirst()
                .orElse(BigDecimal.ZERO);
    }

}
