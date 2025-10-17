package com.foodordermicroservice.domain.valueobject;

import java.math.BigDecimal;

public record PaymentRequest(
        String orderId,
        String customerId,
        BigDecimal amount
) {
}
