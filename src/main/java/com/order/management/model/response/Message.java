package com.order.management.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class Message {
    private String field;
    private String section;
    private String message;
    private String code;
    private String type;
    private String[] args;

    public Message(String type, String code) {
        this.type = type;
        this.code = code;
    }

    public Message(String message, String code, String type) {
        this(type, code);
        this.message = message;
    }

    public Message(String field, String message, String code, String type) {
        this(message, type, code);
        this.field = field;
    }

    public Message(String field, String code, String message, String[] args) {
        this.field = field;
        this.message = message;
        this.code = code;
        this.args = args;
    }

    public static Message getSuccessMessage() {
        return new Message("success", "2000");
    }

    public static Message getFailureMessage(HttpStatus status) {
        return new Message("failure", String.valueOf(status.value()));
    }
}
