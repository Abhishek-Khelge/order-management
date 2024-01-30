package com.order.management.service.validator;


import com.order.management.model.order.OrderRequest;
import com.order.management.model.product.PriceQuoteRequest;
import com.order.management.model.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class OrderValidator {

    @Autowired
    private Validator validator;

    public List<Message> validate(OrderRequest orderRequest) {
        List<Message> toReturn = ValidationUtil.convertViolationsToMessages(validator.validate(orderRequest));


        return toReturn;
    }

}
