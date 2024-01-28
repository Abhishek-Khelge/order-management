package com.order.management.mapper;

import com.order.management.entity.Cart;
import com.order.management.entity.Inventory;
import com.order.management.entity.Order;
import com.order.management.entity.OrderItem;
import com.order.management.model.cart.CartEntryRequestModel;
import com.order.management.model.cart.CartProductsResponseModel;
import com.order.management.model.order.OrderItemResponseModel;
import com.order.management.model.order.OrderResponseModel;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static OrderResponseModel getOrderItemResponseModel(Order currOrder, List<OrderItem> orderItems) {
        return new OrderResponseModel(
                currOrder.getId(),
                OrderItemMapper.getOrderResponseModelList(orderItems),
                currOrder.getCreatedAt(),
                currOrder.getTotalAmount()
        );
    }
}
