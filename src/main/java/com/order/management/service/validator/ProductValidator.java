package com.order.management.service.validator;


import com.order.management.model.product.PriceQuoteRequest;
import com.order.management.model.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class ProductValidator {

    @Autowired
    private Validator validator;

    public List<Message> validate(PriceQuoteRequest priceQuoteRequest) {
        List<Message> toReturn = ValidationUtil.convertViolationsToMessages(validator.validate(priceQuoteRequest));

        if (priceQuoteRequest.getCouponCode().trim().isEmpty()) {
            toReturn.add(new Message("couponCode", "couponCode is empty space", "4001", "failure"));
        }

        return toReturn;
    }

}
