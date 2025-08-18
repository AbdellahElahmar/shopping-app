package com.net.ElAhmar.order_service.service;


import com.net.ElAhmar.order_service.dto.OrderItemDto;
import com.net.ElAhmar.order_service.dto.OrderRequest;
import com.net.ElAhmar.order_service.model.Order;
import com.net.ElAhmar.order_service.model.OrderItem;
import com.net.ElAhmar.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItem> orderLines = orderRequest.getOrderItems()
                .stream()
                .map(this::mapToOrderItem)
                .toList();
        order.setOrderItems(orderLines);
        orderRepository.save(order);
    }

    private OrderItem mapToOrderItem(OrderItemDto item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuCode(item.getSkuCode());
        orderItem.setQuantity(item.getQuantity());
        orderItem.setPrice(item.getPrice());
        return orderItem;
    }


}
