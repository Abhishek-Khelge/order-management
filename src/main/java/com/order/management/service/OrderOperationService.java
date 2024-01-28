package com.order.management.service;

import com.order.management.entity.Inventory;
import com.order.management.entity.Order;
import com.order.management.entity.OrderItem;
import com.order.management.mapper.OrderItemMapper;
import com.order.management.mapper.OrderMapper;
import com.order.management.model.order.OrderItemResponseModel;
import com.order.management.model.order.OrderResponseModel;
import com.order.management.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderOperationService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;

    public Page<OrderResponseModel> getOrders(String userId, Pageable pageable) {
        Page<Order> toReturn = orderRepository.findAllByUserId(userId, pageable);
        if (toReturn == null || toReturn.isEmpty()) {
            return Page.empty();
        }

        List<OrderResponseModel> response = new ArrayList<>();
        toReturn.get().forEach(currOrder -> {
            List<OrderItem> orderItems = orderItemService.getOrderItemsForOrderId(currOrder.getId());
            response.add(OrderMapper.getOrderItemResponseModel(currOrder, orderItems));
        });
        return new PageImpl<>(
                response,
                toReturn.getPageable(),
                toReturn.getTotalElements()
        );
    }

    public OrderItemResponseModel getOrderItemDetails(String orderItemId) {
        OrderItem orderItem = orderItemService.getOrderByOrderItemId(orderItemId);
        return OrderItemMapper.getOrderItemDetailsModel(orderItem);
    }
}
