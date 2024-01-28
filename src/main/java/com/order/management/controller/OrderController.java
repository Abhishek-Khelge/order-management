package com.order.management.controller;

import com.order.management.model.InputListInfo;
import com.order.management.model.ListInfo;
import com.order.management.model.order.OrderItemResponseModel;
import com.order.management.model.order.OrderResponseModel;
import com.order.management.model.response.ResponseModel;
import com.order.management.service.OrderOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class OrderController {

    @Autowired
    private OrderOperationService orderOperationService;

    @GetMapping("/v1/order")
    public ResponseEntity<ResponseModel> getAllOrders(
            @RequestParam(name = "user_id") String userId,
            @RequestParam(name = "list_info", required = false) InputListInfo inputListInfo
    ) {
        ListInfo listInfo = ListInfo.defaultListInfo();
        if (inputListInfo != null) {
            listInfo = new ListInfo(inputListInfo.getPage(), inputListInfo.getPageSize(),
                    inputListInfo.getSortField(), inputListInfo.getSortOrder());
        }
        Map<String, Object> responseMap = new HashMap<>();
        var pagedProductDetails = orderOperationService.getOrders(
                userId,
                ListInfo.toPageable(listInfo)
        );
        responseMap.put("order_detail", pagedProductDetails.toList());
        return ResponseModel.getInstance(
                responseMap,
                HttpStatus.OK,
                ListInfo.getListInfo(pagedProductDetails, listInfo)
        );
    }

    @GetMapping("/v1/order/{order_item_id}")
    public ResponseEntity<ResponseModel> getOrderDetailsById(
            @PathVariable(name = "order_item_id") String orderItemId
    ) {
        OrderItemResponseModel responseModel = orderOperationService.getOrderItemDetails(orderItemId);
        return ResponseModel.getInstance("order_detail", responseModel, HttpStatus.OK);
    }

    @PostMapping("/v1/order/place")
    public ResponseEntity<ResponseModel> placeOrder() {
        return null;
    }

    @PutMapping("/v1/order/detail/{order_id}")
    public ResponseEntity<ResponseModel> updateOrderDetails() {
        return null;
    }

    @PutMapping("/v1/order/cancel")
    public ResponseEntity<ResponseModel> cancelOrder() {
        return null;
    }

    @DeleteMapping("/v1/order/product")
    public ResponseEntity<ResponseModel> deleteProduct() {
        return null;
    }
}
