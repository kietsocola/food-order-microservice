package com.foodordermicroservice.orderservice.domain.valueobject;

import java.math.BigDecimal;

public record OrderItemRequest(
        String orderItemId,
        String productId,
        String productName,
        Integer quantity,
        BigDecimal price
) {
}
