package com.foodordermicroservice.orderservice.application.port;

import com.foodordermicroservice.orderservice.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findOrderById(String orderId);
    Order save(Order order);
    void updateOrderStatus(String orderId, String status);
}
