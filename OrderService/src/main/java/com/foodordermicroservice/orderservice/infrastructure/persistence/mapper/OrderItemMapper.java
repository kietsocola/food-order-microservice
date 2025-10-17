package com.foodordermicroservice.orderservice.infrastructure.persistence.mapper;

import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.ProductId;
import com.foodordermicroservice.common.share.dto.OrderItemResponse;
import com.foodordermicroservice.orderservice.domain.entity.OrderItem;
import com.foodordermicroservice.orderservice.domain.valueobject.OrderItemRequest;

import java.util.UUID;

public class OrderItemMapper {
    public static OrderItem toDomain(OrderItemRequest request) {
        return OrderItem.builder()
                .productId(new ProductId(UUID.fromString(request.productId())))
                .productName(request.productName())
                .quantity(request.quantity())
                .price(new Money(request.price()))
                .build(
        );
    }

    public static OrderItemResponse toDto(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getOrderId() != null ? orderItem.getOrderId().getValue() : null)
                .productId(orderItem.getProductId() != null ? orderItem.getProductId() : null)
                .productName(orderItem.getProductName())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice() != null ? orderItem.getPrice().getAmount() : null)
                .subTotal(orderItem.getSubTotal() != null ? orderItem.getSubTotal().getAmount() : null)
                .build();
    }
}
