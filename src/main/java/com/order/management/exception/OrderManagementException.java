package com.order.management.exception;

import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class OrderManagementException extends Exception {
    private OrderManagementError orderManagementError;
    private String[] args;
    private Object data;

    private static final String DEFAULT_MESSAGE = "Internal Error";
    private static final Map<String, String> ERROR_MESSAGES = Map.of(
            "40001", "%1$2s not found for entity id %2$2s",
            "40002", "%1$2s already exists for %2$2s with id %3$2s",
            "40004" , "The provided page number for the request is invalid. Please provide a valid page number",
            // 40201 - 4500

            "40201", "product does not exist in inventory for the given serial number %1$2s",
            "40202", "product quantity can't be decreased less than 0 for given serial number %1$2s",
            "40203", "product already exits for for given serial number %1$2s, the serial number should be unique, try updating the product details",

            // ... (other error messages)
            "500", "Internal Server Error"
    );

    public OrderManagementException(OrderManagementError orderManagementError, String[] args, String internalMessage, Throwable cause) {
        super(orderManagementError.getErrorCode(), cause);
        this.orderManagementError = orderManagementError;
        this.args = args;
        this.data = null;
    }

    public OrderManagementException(OrderManagementError orderManagementError, String[] args) {
        this(orderManagementError, args, null, null);
    }

    public OrderManagementException(OrderManagementError orderManagementError, String[] args, Object data) {
        this(orderManagementError, args, null, null);
        this.data = data;
    }

    public String getClientErrorMessage() {
        if (orderManagementError.getHttpStatus().is4xxClientError() && ERROR_MESSAGES.containsKey(orderManagementError.getErrorCode())) {
            String message = ERROR_MESSAGES.get(orderManagementError.getErrorCode());
            message = (message != null) ? message : DEFAULT_MESSAGE;
            String[] args = getArgs();
            if (args.length > 0) {
                return String.format(message, (Object[]) args);
            } else {
                return message;
            }
        } else {
            return DEFAULT_MESSAGE;
        }
    }

    public OrderManagementError getOrderManagementError() {
        return orderManagementError;
    }

    public String[] getArgs() {
        List<String> sanitizedArgs = new ArrayList<>();
        if (args != null) {
            for (String arg : args) {
                sanitizedArgs.add(HtmlUtils.htmlEscape(arg));
            }
        }
        return sanitizedArgs.toArray(new String[0]);
    }

    public String getInternalErrorMessage() {
        if (orderManagementError.getHttpStatus().is5xxServerError() && ERROR_MESSAGES.containsKey(orderManagementError.getErrorCode())) {
            String message = ERROR_MESSAGES.get(orderManagementError.getErrorCode());
            message = (message != null) ? message : DEFAULT_MESSAGE;
            String[] args = getArgs();
            if (args.length > 0) {
                return new Formatter().format(message, (Object[]) args).toString();
            } else {
                return message;
            }
        } else {
            return "Internal Server Error";
        }
    }
}
