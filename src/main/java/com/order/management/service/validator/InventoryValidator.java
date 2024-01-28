package com.order.management.service.validator;


import com.order.management.model.inventory.AddProductRequestModel;
import com.order.management.model.inventory.UpdateProductRequestModel;
import com.order.management.model.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class InventoryValidator {

    @Autowired
    private Validator validator;

    public List<Message> validate(AddProductRequestModel productInputModel) {
        List<Message> toReturn = ValidationUtil.convertViolationsToMessages(validator.validate(productInputModel));

        if (productInputModel.getProductName().isEmpty()) {
            toReturn.add(new Message("product_name", "product_name can't be empty", "4001", "failure"));
        }

        if (productInputModel.getSerialNumber().isEmpty()) {
            toReturn.add(new Message("serial_number", "serial_number can't be empty", "4001", "failure"));
        }

        return toReturn;
    }

    public List<Message> validate(UpdateProductRequestModel updateProductRequestModel) {
        List<Message> toReturn = ValidationUtil.convertViolationsToMessages(validator.validate(updateProductRequestModel));

        if (updateProductRequestModel.getDecreaseQuantityBy() != 0 &&
                updateProductRequestModel.getIncreaseQuantityBy() != 0) {
            toReturn.add(new Message("quantity", "product quantity can't be increased and decreased at" +
                    " the same time ", "4001", "failure"));
        }

        if (updateProductRequestModel.getProductName() == null && updateProductRequestModel.getPrice() == null &&
                updateProductRequestModel.getDecreaseQuantityBy() == 0 &&
                updateProductRequestModel.getIncreaseQuantityBy() == 0 && updateProductRequestModel.getDescription() == null) {
            toReturn.add(new Message("input", "nothing to update to", "4001", "failure"));
        }

        return toReturn;
    }
}
