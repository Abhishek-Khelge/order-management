package com.order.management.service.validator;

import com.order.management.common.StringUtil;
import com.order.management.model.response.Message;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationUtil {
    public static final int VALIDATION_FAILED_STATUS_CODE = 400;

    /**
     * Convert field validation violations into error messages to be sent to the client
     *
     * @param violations Set of [ConstraintViolation] for the fields
     * @return [List] List of [Message]
     */
    public static List<Message> convertViolationsToMessages(Set<ConstraintViolation<Object>> violations) {
        List<Message> messages = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Object> violation : violations) {
                messages.add(
                        new Message(
                                StringUtil.camelCaseToSnakeCase(violation.getPropertyPath().toString()),
                                violation.getMessage(),
                                "4000",
                                "failure"
                        )
                );
            }
        }
        return messages;
    }

}
