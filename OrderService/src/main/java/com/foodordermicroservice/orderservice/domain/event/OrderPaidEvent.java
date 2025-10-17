package com.foodordermicroservice.orderservice.domain.event;

import com.foodordermicroservice.common.domain.event.DomainEvent;
import com.foodordermicroservice.orderservice.domain.entity.Order;

public class OrderPaidEvent extends DomainEvent<Order> {
    public OrderPaidEvent(Order order) {
        super(order);
    }
}
