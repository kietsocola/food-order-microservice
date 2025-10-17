package com.foodordermicroservice.orderservice.infrastructure.persistence.mapper;

import com.foodordermicroservice.common.domain.valueobject.CustomerId;
import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.common.domain.valueobject.VenuesId;
import com.foodordermicroservice.orderservice.api.dto.OrderResponse;
import com.foodordermicroservice.orderservice.domain.entity.Order;
import com.foodordermicroservice.orderservice.domain.valueobject.OrderRequest;
import com.foodordermicroservice.orderservice.infrastructure.persistence.entity.OrderEntity;

import java.util.UUID;

public class OrderMapper {
    public static Order toOrderDomain(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        return Order.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .customerId(new CustomerId(UUID.fromString(orderEntity.getCustomerId())))
                .venuesId(new VenuesId(UUID.fromString(orderEntity.getVenueId())))
                .orderStatus(orderEntity.getOrderStatus())
                .totalPrice(new Money(orderEntity.getTotalPrice()))
                .totalQuantity(orderEntity.getTotalQuantity())
                .code(orderEntity.getCode())
                .orderDate(orderEntity.getOrderDate())
                .orderStatus(orderEntity.getOrderStatus())
                .failureMessages(orderEntity.getFailureMessages())
                .build();
    }

    public static Order toOrderDomain(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }
        return Order.builder()
                .customerId(new CustomerId(UUID.fromString(orderRequest.customerId())))
                .venuesId(new VenuesId(UUID.fromString(orderRequest.venuesId())))
                .orderItems(orderRequest.orderItems().stream()
                        .map(OrderItemMapper::toDomain)
                        .toList())
                .address(orderRequest.address())
                .build();
    }

    public static OrderEntity toOrderEntity(Order order) {
        if (order == null) {
            return null;
        }
        return OrderEntity.builder()
                .id(order.getId() != null ? order.getId().getValue() : null)
                .customerId(order.getCustomerId() != null ? order.getCustomerId().getValue().toString() : null)
                .venueId(order.getVenuesId() != null ? order.getVenuesId().getValue().toString() : null)
                .totalPrice(order.getTotalPrice() != null ? order.getTotalPrice().getAmount() : null)
                .totalQuantity(order.getTotalQuantity())
                .code(order.getCode())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public static OrderResponse fromOrder(Order order) {
        if (order != null) {
            return OrderResponse.builder()
                    .id(order.getId() != null ? order.getId().getValue() : null)
                    .customerId(order.getCustomerId())
                    .venueId(order.getVenuesId())
                    .totalPrice(order.getTotalPrice() != null ? order.getTotalPrice().getAmount() : null)
                    .totalQuantity(order.getTotalQuantity())
                    .code(order.getCode())
                    .orderDate(order.getOrderDate())
                    .orderStatus(order.getOrderStatus())
                    .failureMessages(order.getFailureMessages())
                    .orderItems(order.getOrderItems() != null ? order.getOrderItems().stream()
                            .map(OrderItemMapper::toDto)
                            .toList() : null)
                    .build();
        }
        return null;
    }
}
