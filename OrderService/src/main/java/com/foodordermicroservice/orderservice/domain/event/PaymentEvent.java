package com.foodordermicroservice.orderservice.domain.event;

import com.foodordermicroservice.common.domain.event.DomainEvent;
import com.foodordermicroservice.orderservice.domain.entity.Order;
import com.foodordermicroservice.orderservice.domain.entity.Payment;

public class PaymentEvent extends DomainEvent<Payment> {
    public PaymentEvent(Payment payment) {
        super(payment);
    }
}
