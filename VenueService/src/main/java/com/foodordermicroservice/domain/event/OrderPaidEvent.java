package com.foodordermicroservice.domain.event;

import com.foodordermicroservice.common.domain.event.DomainEvent;
import com.foodordermicroservice.domain.entity.Order;

public class OrderPaidEvent extends DomainEvent<Order> {
    public OrderPaidEvent(Order order) {
        super(order);
    }
}