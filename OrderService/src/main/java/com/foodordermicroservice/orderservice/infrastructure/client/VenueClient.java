package com.foodordermicroservice.orderservice.infrastructure.client;

import org.springframework.web.bind.annotation.PathVariable;

public interface VenueClient {
    boolean checkVenueExists(@PathVariable String id);
}
