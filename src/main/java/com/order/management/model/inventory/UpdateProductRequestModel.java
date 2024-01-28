package com.order.management.model.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class UpdateProductRequestModel {
    private String productName;
    private BigDecimal price;
    private int increaseQuantityBy;
    private int decreaseQuantityBy;
    private String description;
}
