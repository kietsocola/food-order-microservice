package com.foodordermicroservice.orderservice.domain.valueobject;

import java.util.List;

public record OrderRequest(String customerId,
                           String venuesId,
                           String address,
                           List<OrderItemRequest> orderItems) {
}
