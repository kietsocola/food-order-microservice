package com.foodordermicroservice.presentation.dto;

public record LocationMessage (
        String orderId,
        String venueId,
        String address
) {
}
