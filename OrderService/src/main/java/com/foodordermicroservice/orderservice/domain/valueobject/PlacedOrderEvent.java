package com.foodordermicroservice.orderservice.domain.valueobject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlacedOrderEvent(String orderId,
                               String customerId,
                               String venuesId,
                               BigDecimal totalPrice,
                               String address,
                               LocalDateTime orderDate,
                               String status) {
}
