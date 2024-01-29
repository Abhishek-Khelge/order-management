package com.order.management.service.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
public class InventoryValidator {

    @Autowired
    private Validator validator;

//    public List<Message> validate(AddProductRequestModel productInputModel) {
//        List<Message> toReturn = ValidationUtil.convertViolationsToMessages(validator.validate(productInputModel));
//
//        if (productInputModel.getProductName().isEmpty()) {
//            toReturn.add(new Message("product_name", "product_name can't be empty", "4001", "failure"));
//        }
//
//        if (productInputModel.getSerialNumber().isEmpty()) {
//            toReturn.add(new Message("serial_number", "serial_number can't be empty", "4001", "failure"));
//        }
//
//        return toReturn;
//    }

}
