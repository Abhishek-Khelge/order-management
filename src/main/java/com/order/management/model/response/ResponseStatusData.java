package com.order.management.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.order.management.exception.OrderManagementException;
import com.order.management.model.ListInfo;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class ResponseStatusData {

    private int statusCode;
    private List<Message> messages;
    private String status;
    private ListInfo listInfo;

    public static ResponseStatusData getInstance(HttpStatus httpStatus, Throwable exception, ListInfo listInfo) {
        boolean isError = exception != null;
        if (!isError) {
            isError = httpStatus.isError();
        }

        int statusCode = 200;
        List<Message> message = new ArrayList<>();
        String statusMsg;

        if (isError) {
            if (exception != null) {
                if (exception instanceof OrderManagementException) {
                    var orderManagementError = ((OrderManagementException) exception).getOrderManagementError();
                    statusCode = orderManagementError.getHttpStatus().value();
                    if (httpStatus.is5xxServerError()) {
                        message.add(new Message(
                                ((OrderManagementException) exception).getInternalErrorMessage(),
                                orderManagementError.getErrorCode(),
                                "failure"
                        ));
                    } else {
                        message.add(new Message(
                                ((OrderManagementException) exception).getClientErrorMessage(),
                                orderManagementError.getErrorCode(),
                                "failure",
                                ((OrderManagementException) exception).getArgs()
                        ));
                    }
                } else {
                    String code = httpStatus.is4xxClientError() ? "4000" : "5000";
                    message.add(new Message(
                            httpStatus.getReasonPhrase(),
                            "failure",
                            code
                    ));
                    statusCode = httpStatus.value();
                }
            } else {
                statusCode = httpStatus.value();
                message.add(new Message("failure", "" + httpStatus.value()));
            }
            statusMsg = "failed";
        } else {
            message.add(new Message("success", "2000"));
            statusCode = httpStatus.value();
            statusMsg = "success";
        }

        return new ResponseStatusData(statusCode, message, statusMsg, listInfo);
    }
}
