package com.foodordermicroservice.domain.valueobject;

import java.util.List;

public record VenueRequest(
        String venueName,
        String venueAddress,
        List<ProductRequest> products
) {
}
