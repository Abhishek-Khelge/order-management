package com.order.management.controller;

import com.order.management.model.product.PriceQuoteRequest;
import com.order.management.model.product.PriceQuoteResponse;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/v1/products/{product_id}/price-quote")
    public ResponseEntity<ResponseModel> getProductPriceQuote(
            @PathVariable("product_id") Integer productId,
            @RequestParam(name = "coupon_code", required = false) String couponCode,
            @RequestParam(name = "include_tax", defaultValue = "false") boolean includeTax,
            @RequestParam(name = "distance", required = false) BigDecimal distance,
            @RequestParam(name = "include_platform_fee", defaultValue = "false") boolean includePlatformFee
    ) {

        PriceQuoteRequest priceQuoteRequest = new PriceQuoteRequest(
                couponCode, includeTax, distance, includePlatformFee
        );

        PriceQuoteResponse priceQuoteResponse = productService.calculatePriceQuote(productId, priceQuoteRequest);
        return ResponseModel.getInstance("body", priceQuoteResponse, HttpStatus.OK);
    }
}