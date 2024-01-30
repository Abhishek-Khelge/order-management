package com.order.management.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.order.management.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
public class NotificationModel {
    private String orderId;
    private OrderStatus orderStatus;
}
