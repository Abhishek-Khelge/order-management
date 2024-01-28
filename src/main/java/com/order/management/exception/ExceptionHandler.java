package com.order.management.exception;

import com.order.management.model.response.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Throwable.class)
    public ResponseEntity<ResponseModel> handleAllExceptions(Throwable throwable) {
        HttpStatus httpStatus = null;

        if (throwable instanceof OrderManagementException orderManagementException) {
            log.error("{} Message: {} Error: {} ClassName: {} LineNumber: {} ",
                    orderManagementException.toString(), orderManagementException.getClientErrorMessage(),
                    orderManagementException.getInternalErrorMessage(),
                    orderManagementException.getStackTrace()[0].getClassName(),
                    orderManagementException.getStackTrace()[0].getLineNumber());

            httpStatus = orderManagementException.getOrderManagementError().getHttpStatus();
            return ResponseModel.getInstanceForException(orderManagementException, httpStatus);
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            log.error("Throwing internal server error: {}", (Object) throwable.getStackTrace());
            log.error(throwable.getMessage(), throwable);
        }
        return ResponseModel.getInstanceForException(throwable, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        switch (exception.getClass().getName()) {
            case "OrderManagementException":
                OrderManagementException orderManagementException = (OrderManagementException) exception;
                ResponseEntity<ResponseModel> responseModel = ResponseModel.getInstanceForException(orderManagementException, status);
                return ResponseEntity.status(status)
                        .headers(responseModel.getHeaders())
                        .body(responseModel.getBody());
            default:
                if (status.is5xxServerError()) {
                    log.error(exception.getMessage(), exception);
                } else {
                    log.error(exception.getMessage(), exception);
                }
        }

        ResponseEntity<ResponseModel> responseModel = ResponseModel.getInstanceForException(exception, status);
        return ResponseEntity.status(status)
                .headers(responseModel.getHeaders())
                .body(responseModel.getBody());
    }


}
