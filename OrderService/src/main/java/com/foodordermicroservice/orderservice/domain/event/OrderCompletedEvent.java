package com.foodordermicroservice.orderservice.domain.event;

import com.foodordermicroservice.common.domain.event.DomainEvent;
import com.foodordermicroservice.orderservice.domain.entity.Order;

public class OrderCompletedEvent extends DomainEvent<Order> {
    public OrderCompletedEvent(Order order) {
        super(order);
    }
}
