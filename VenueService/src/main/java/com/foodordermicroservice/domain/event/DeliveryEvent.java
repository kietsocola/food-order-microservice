package com.foodordermicroservice.domain.event;

import com.foodordermicroservice.common.domain.valueobject.OrderStatus;

import java.time.LocalDateTime;

public record DeliveryEvent(
        String orderId,
        String customerId,
        String address,
        OrderStatus orderStatus,
        Double latitude,
        Double longitude,
        LocalDateTime eventTime
) {
}
