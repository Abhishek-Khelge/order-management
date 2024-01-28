package com.order.management.exception;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum OrderManagementError {
    ENTITY_NOT_FOUND("40001", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_NUMBER("40004", HttpStatus.BAD_REQUEST),

    // 40201 - 4500
    PRODUCT_DOES_NOT_EXISTS("40201", HttpStatus.BAD_REQUEST),
    PRODUCT_QUANTITY_DECREASE_LESS_THAN_ZERO("40202", HttpStatus.BAD_REQUEST),
    PRODUCT_ALREADY_EXISTS("40203", HttpStatus.BAD_REQUEST),
    PRODUCT_ALREADY_EXISTS_IN_CART("40204", HttpStatus.BAD_REQUEST),
    PRODUCT_DOES_NOT_EXISTS_IN_CART("40205", HttpStatus.BAD_REQUEST),
    PRODUCT_UPDATE_QUANTITY_DECREASE_LESS_THAN_ZERO("40206", HttpStatus.BAD_REQUEST),
    PRODUCT_ALREADY_EXISTS_IN_CART_A("40204", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final HttpStatus httpStatus;

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}