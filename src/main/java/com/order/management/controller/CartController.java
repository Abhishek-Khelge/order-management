package com.order.management.controller;

import com.order.management.model.InputListInfo;
import com.order.management.model.ListInfo;
import com.order.management.model.cart.CartEntryRequestModel;
import com.order.management.model.cart.CartProductsResponseModel;
import com.order.management.model.cart.UpdateCartRequestModel;
import com.order.management.model.response.Message;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.CartProductService;
import com.order.management.service.validator.CartValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
public class CartController {
    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private CartValidator cartValidator;

    @GetMapping("/v1/cart")
    public ResponseEntity<ResponseModel> getCartProducts(
            @RequestParam(name = "user_id") String userId,
            @RequestParam(name = "list_info", required = false) InputListInfo inputListInfo
    ) {
        ListInfo listInfo = ListInfo.defaultListInfo();
        if (inputListInfo != null) {
            listInfo = new ListInfo(inputListInfo.getPage(), inputListInfo.getPageSize(),
                    inputListInfo.getSortField(), inputListInfo.getSortOrder());
        }
        var pagedCartProducts = cartProductService.getCartProducts(
                userId,
                ListInfo.toPageable(listInfo)
        );
        return ResponseModel.getInstance(
                new HashMap<>() {{
                    put("cart_product", pagedCartProducts.toList());
                }},
                HttpStatus.OK,
                ListInfo.getListInfo(pagedCartProducts, listInfo));
    }

    @PostMapping("/v1/cart")
    public ResponseEntity<ResponseModel> addProduct(
            @RequestParam(name = "user_id") String userId,
            @RequestBody CartEntryRequestModel cartInputModel) {
        List<Message> messages = cartValidator.validate(cartInputModel);
        if (!messages.isEmpty()) {
            return ResponseModel.getInstance(messages, HttpStatus.BAD_REQUEST);
        }
        CartProductsResponseModel responseModel = cartProductService.addProductToCart(userId, cartInputModel);
        return ResponseModel.getInstance("cart", responseModel, HttpStatus.CREATED);
    }

    @PutMapping("/v1/cart/{serial_number}")
    public ResponseEntity<ResponseModel> updateProduct(
            @RequestParam(name = "user_id") String userId,
            @PathVariable(name = "serial_number") String serialNumber,
            @RequestBody UpdateCartRequestModel updateCartRequestModel
    ) {
        List<Message> messages = cartValidator.validate(updateCartRequestModel);
        if (!messages.isEmpty()) {
            return ResponseModel.getInstance(messages, HttpStatus.BAD_REQUEST);
        }
        CartProductsResponseModel responseModel = cartProductService.updateCart(userId, serialNumber, updateCartRequestModel);
        return ResponseModel.getInstance("cart", responseModel, HttpStatus.OK);
    }

    @DeleteMapping("/v1/cart/{serial_number}")
    public ResponseEntity<ResponseModel> deleteProduct(
            @RequestParam(name = "user_id") String userId,
            @PathVariable(name = "serial_number") String serialNumber
    ) {
        CartProductsResponseModel responseModel = cartProductService.removeProduct(userId, serialNumber);
        return ResponseModel.getInstance("inventory_removed", responseModel, HttpStatus.OK);
    }
}
