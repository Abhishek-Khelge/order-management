package com.order.management.controller;

import com.order.management.model.InputListInfo;
import com.order.management.model.ListInfo;
import com.order.management.model.inventory.*;
import com.order.management.model.response.Message;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.ProductService;
import com.order.management.service.validator.InventoryValidator;
import com.order.management.swagger.SwaggerExample;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class InventoryController {

    @Autowired
    private InventoryValidator inventoryValidator;
    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Search Products",
            description = "Get products from inventory for the given query",
            tags = {"INVENTORY"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product Details fetched successfully for the given query",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.GET_PRODUCT_DETAILS_RESPONSE),
                                    schema = @Schema(implementation = ProductResponseModel.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An Internal Server Error has occurred",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE)
                            )
                    )
            }
    )
    @io.swagger.v3.oas.annotations.Parameters({
            @Parameter(
                    name = "search_query",
                    required = true,
                    description = "product search query, like some part of the name",
                    examples = {
                            @ExampleObject(
                                    name = "la",
                                    description = "returns product names containing la",
                                    value = "lamp, clamp"
                            ),
                            @ExampleObject(
                                    name = "o",
                                    description = "returns product names containing o",
                                    value = "book, orange"
                            )
                    },
                    schema = @Schema(implementation = String.class)
            ),
            @Parameter(
                    name = "list_info",
                    required = false,
                    description = "Configure Pagination & Response Ordering of the response",
                    examples = {
                            @ExampleObject(
                                    name = "Order By product Descending (10 Values)",
                                    description = "Ensure that the value is URL Encoded and sent",
                                    value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE
                            ),
                            @ExampleObject(
                                    name = "Page 1 & Page Count 100",
                                    description = "Ensure that the value is URL Encoded and sent",
                                    value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE
                            )
                    },
                    schema = @Schema(implementation = ListInfo.class)
            )
    })
    @GetMapping("/v1/product")
    public ResponseEntity<ResponseModel> getProduct(
            @RequestParam(name = "search_query") String searchQuery,
            @RequestParam(name = "list_info", required = false) InputListInfo inputListInfo
    ) {
        var decodedSearchQuery = URLDecoder.decode(searchQuery, StandardCharsets.UTF_8);
        ListInfo listInfo = ListInfo.defaultListInfo();
        if (inputListInfo != null) {
            listInfo = new ListInfo(inputListInfo.getPage(), inputListInfo.getPageSize(),
                    inputListInfo.getSortField(), inputListInfo.getSortOrder());
        }
        Map<String, Object> responseMap = new HashMap<>();
        var pagedProductDetails = productService.searchBySearchQuery(
                decodedSearchQuery,
                ListInfo.toPageable(listInfo)
        );
        responseMap.put("products_details", pagedProductDetails.toList());
        return ResponseModel.getInstance(
                responseMap,
                HttpStatus.OK,
                ListInfo.getListInfo(pagedProductDetails, listInfo)
        );
    }


    @Operation(
            summary = "Product Details",
            description = "Get product details for the given serial number",
            tags = {"INVENTORY"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product Details fetched successfully for the given serial number",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.GET_BY_SN_RESPONSE),
                                    schema = @Schema(implementation = ProductResponseModel.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "The given serial number, does not exist or is deleted",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.GET_BY_SN_NOT_EXIST_RESPONSE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An Internal Server Error has occurred",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE)
                            )
                    )
            }
    )
    @GetMapping("/v1/product/{serial_number}")
    public ResponseEntity<ResponseModel> getProductBySerialNum(@PathVariable(name = "serial_number") String serialNumber) {
        ProductResponseModel responseModel = productService.getProductDetails(serialNumber);
        return ResponseModel.getInstance("products_details", responseModel, HttpStatus.OK);
    }

    @Operation(
            summary = "Add Product",
            description = "add a product to the inventory",
            tags = {"INVENTORY"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product Details fetched successfully for the given serial number",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.PRODUCT_DETAILS_RESPONSE),
                                    schema = @Schema(implementation = ProductResponseModel.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product details already exist for the given serial number",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.PRODUCT_DETAILS_ALREADY_SN_RESPONSE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An Internal Server Error has occurred",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE)
                            )
                    )
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = SwaggerExample.ADD_PRODUCT_REQUEST)
                    },
                    schema = @Schema(implementation = AddProductRequestModel.class)
            )
    )
    @PostMapping("/v1/product")
    public ResponseEntity<ResponseModel> addProduct(@RequestBody AddProductRequestModel productInputModel) {
        List<Message> messages = inventoryValidator.validate(productInputModel);
        if (!messages.isEmpty()) {
            return ResponseModel.getInstance(messages, HttpStatus.BAD_REQUEST);
        }
        AddProductResponseModel responseModel = productService.addProduct(productInputModel);
        return ResponseModel.getInstance("inventory_product", responseModel, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update Product Details",
            description = "Get product details for the given serial number",
            tags = {"INVENTORY"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product Details fetched successfully for the given serial number",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.UPDATE_PRODUCT_RESPONSE),
                                    schema = @Schema(implementation = UpdateProductResponseModel.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input for update product",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INVALID_INPUT_UPDATE_PRODUCT_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product details does not exist for the given serial number",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INVALID_INPUT_SN_UPDATE_PRODUCT_REQUEST)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An Internal Server Error has occurred",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE)
                            )
                    )
            }
    )
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "",
            content = @Content(
                    mediaType = "application/json",
                    examples = {
                            @ExampleObject(value = SwaggerExample.UPDATE_PRODUCT_REQUEST)
                    },
                    schema = @Schema(implementation = UpdateProductRequestModel.class)
            )
    )
    @PutMapping("/v1/product/{serial_number}")
    public ResponseEntity<ResponseModel> updateProduct(@PathVariable(name = "serial_number") String serialNumber,
                                                       @RequestBody UpdateProductRequestModel updateProductRequestModel) {
        List<Message> messages = inventoryValidator.validate(updateProductRequestModel);
        if (!messages.isEmpty()) {
            return ResponseModel.getInstance(messages, HttpStatus.BAD_REQUEST);
        }
        UpdateProductResponseModel responseModel = productService.updateProduct(serialNumber, updateProductRequestModel);
        return ResponseModel.getInstance("inventory_product", responseModel, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Product",
            description = "soft deltes a product from the inventory for the specified serial number",
            tags = {"INVENTORY"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Product successfully deleted",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.DELETE_PRODUCT_RESPONSE),
                                    schema = @Schema(implementation = ProductResponseModel.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Product details does not exist for the given serial number",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.DELETE_NOT_THERE_PRODUCT_RESPONSE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An Internal Server Error has occurred",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = SwaggerExample.INTERNAL_SERVER_ERROR_RESPONSE)
                            )
                    )
            }
    )
    @DeleteMapping("/v1/product/{serial_number}")
    //todo : check whether this should be delete or update are we are soft deleting
    public ResponseEntity<ResponseModel> deleteProduct(@PathVariable(name = "serial_number") String serialNumber) {
        UpdateProductResponseModel responseModel = productService.removeProduct(serialNumber);
        return ResponseModel.getInstance("inventory_removed", responseModel, HttpStatus.OK);
    }


}
