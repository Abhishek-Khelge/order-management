package com.order.management.model.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.order.management.model.ListInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@Builder
public class ResponseModel {
    private Map<String, Object> dynamicResponse;
    private ResponseMetaData metadata;
    private ResponseStatusData responseStatus;

    @JsonAnyGetter
    public Map<String, Object> getDynamicResponse() {
        return dynamicResponse;
    }


    public static ResponseEntity<ResponseModel> getInstanceForException(
            Throwable throwable,
            HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ResponseModel(null, null, ResponseStatusData.getInstance(httpStatus, throwable, null)),
                httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    public static ResponseEntity<ResponseModel> getInstance(List<Message> messages, HttpStatus status) {
        return new ResponseEntity<>(
                ResponseModel.builder().responseStatus(ResponseStatusData.builder().statusCode(status.value())
                        .messages(messages).status("failed").build()).build(),
                status
        );
    }

    /**
     * Get Response Model Instance
     *
     * @param entity         entity name
     * @param entityResponse response from the server
     * @param status         http status code for the request
     * @param listInfo       listInfo
     * @return [ResponseModel]
     */
    public static ResponseEntity<ResponseModel> getInstance(
            String entity,
            Object entityResponse,
            HttpStatus status,
            ListInfo listInfo) {

        String responseKey = (entityResponse instanceof Object[]) ? entity + "s" : entity;
        return getInstance(Map.of(responseKey, entityResponse), status, listInfo);
    }

    public static ResponseEntity<ResponseModel> getInstance(
            String entity,
            Object entityResponse,
            HttpStatus status) {

        String responseKey = (entityResponse instanceof Object[]) ? entity + "s" : entity;
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put(responseKey, entityResponse);
        return getInstance(responseMap, status, null);
    }

    /**
     * Get Response Model Instance
     *
     * @param response request response Map
     * @param status   http status code for the request
     * @return [ResponseModel]
     */
    public static ResponseEntity<ResponseModel> getInstance(
            Map<String, Object> response,
            HttpStatus status,
            ListInfo listInfo) {

        return new ResponseEntity<>(
                new ResponseModel(
                        response,
                        null,
                        ResponseStatusData.getInstance(status, null, listInfo)
                ),
                status != null ? status : HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}


