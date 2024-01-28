package com.order.management.model.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class AddProductRequestModel {
    @NotNull(message = "Serial number is mandatory for adding product")
    private String serialNumber;
    @NotNull(message = "Product Name is mandatory for adding product")
    private String productName;
    @NotNull(message = "Price is mandatory for adding product")
    private BigDecimal price;
    @Min(value = 1, message = "Invalid quantity")
    private int quantity;
    private String description;
}
