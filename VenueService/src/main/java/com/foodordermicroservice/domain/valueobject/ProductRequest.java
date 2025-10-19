package com.foodordermicroservice.domain.valueobject;

import java.math.BigDecimal;

public record ProductRequest(
        String name,
        String description,
        String venueId,
        BigDecimal price
) {
}
