package com.order.management.mapper;

import com.order.management.entity.OrderItem;
import com.order.management.model.order.OrderItemResponseModel;
import com.order.management.model.order.OrderResponseModel;

import java.util.ArrayList;
import java.util.List;

public class OrderItemMapper {

    public static List<OrderItemResponseModel> getOrderResponseModelList(List<OrderItem> orderItemList) {
        List<OrderItemResponseModel> responseModelList = new ArrayList<>();
        orderItemList.forEach(currOrderItem -> responseModelList.add(
                new OrderItemResponseModel(
                        currOrderItem.getId(),
                        currOrderItem.getProductId(),
                        currOrderItem.getOrderPrice(),
                        currOrderItem.getOrderStatus()
                )
        ));
        return responseModelList;
    }

    public static OrderItemResponseModel getOrderItemDetailsModel(OrderItem orderItem) {
        return new OrderItemResponseModel(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getOrderPrice(),
                orderItem.getOrderStatus()
        );
    }
}
