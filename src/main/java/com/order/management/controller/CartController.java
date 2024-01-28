package com.order.management.controller;

import com.order.management.model.response.ResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
public class CartController {

    @GetMapping("/v1/cart/product")
    public ResponseModel getCartProducts() {
        return null;
    }

    @GetMapping("/v1/cart/product/{serial_number}")
    public ResponseModel getProductBySerialNum() {
        return null;
    }

    @PostMapping("/v1/cart/product")
    public ResponseModel addProduct() {
        return null;
    }

    @PutMapping("/v1/cart/product")
    public ResponseModel updateProduct() {
        return null;
    }

    @DeleteMapping("/v1/cart/product")
    public ResponseModel deleteProduct() {
        return null;
    }
}
