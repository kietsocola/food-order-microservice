package com.foodordermicroservice.orderservice.infrastructure.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public interface CustomerClient {
    boolean checkCustomerExists(@PathVariable String id);
}
