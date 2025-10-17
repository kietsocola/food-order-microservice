package com.foodordermicroservice.domain.event;

import com.foodordermicroservice.common.domain.event.DomainEvent;
import com.foodordermicroservice.domain.entity.Order;

public class OrderCreatedEvent extends DomainEvent<Order> {
    public OrderCreatedEvent(Order order) {
        super(order);
    }
}
