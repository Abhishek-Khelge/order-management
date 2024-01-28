package com.order.management.service.validator;


import com.order.management.model.cart.CartEntryRequestModel;
import com.order.management.model.cart.UpdateCartRequestModel;
import com.order.management.model.response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class CartValidator {

    @Autowired
    private Validator validator;

    public List<Message> validate(CartEntryRequestModel cartEntryModel) {
        return ValidationUtil.convertViolationsToMessages(validator.validate(cartEntryModel));
    }

    public List<Message> validate(UpdateCartRequestModel updateCartRequestModel) {
        List<Message> toReturn = ValidationUtil.convertViolationsToMessages(validator.validate(updateCartRequestModel));
        if (updateCartRequestModel.getDecreaseQuantityBy() != 0 &&
                updateCartRequestModel.getIncreaseQuantityBy() != 0) {
            toReturn.add(new Message("quantity", "product quantity can't be increased and decreased at" +
                    " the same time ", "4001", "failure"));
        }
        if (updateCartRequestModel.getDecreaseQuantityBy() == 0 &&
                updateCartRequestModel.getIncreaseQuantityBy() == 0) {
            toReturn.add(new Message("input", "nothing to update to", "4001", "failure"));
        }
        return toReturn;
    }
}
