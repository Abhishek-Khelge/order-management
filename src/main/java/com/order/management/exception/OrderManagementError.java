package com.order.management.exception;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum OrderManagementError {
    ENTITY_NOT_FOUND("40001", HttpStatus.BAD_REQUEST),
    INVALID_PAGE_NUMBER("40004", HttpStatus.BAD_REQUEST),

    // 40201 - 4500
    PRODUCT_DOES_NOT_EXISTS("40201", HttpStatus.BAD_REQUEST),
    PRODUCT_QUANTITY_EXCEEDED("40202", HttpStatus.BAD_REQUEST),
    PRODUCT_ALREADY_EXISTS("40203", HttpStatus.BAD_REQUEST),


    // 500 error codes

    PRICE_COMPUTE_FAILED("50001", HttpStatus.INTERNAL_SERVER_ERROR),
    ORDER_FAILED("50002", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_UPDATE_QUANTITY_DECREASE_LESS_THAN_ZERO("40206", HttpStatus.BAD_REQUEST);



    private final String errorCode;
    private final HttpStatus httpStatus;

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}