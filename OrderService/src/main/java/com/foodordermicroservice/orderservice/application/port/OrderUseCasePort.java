package com.foodordermicroservice.orderservice.application.port;

import com.foodordermicroservice.orderservice.api.dto.OrderResponse;
import com.foodordermicroservice.orderservice.domain.valueobject.OrderRequest;

public interface OrderUseCasePort {
    OrderResponse createOrder(OrderRequest orderRequest);
    void updateOrderStatus(String orderId, String status);
}