package com.foodordermicroservice.domain.event;

import com.foodordermicroservice.common.domain.event.DomainEvent;
import com.foodordermicroservice.domain.entity.Payment;

public class PaymentEvent extends DomainEvent<Payment> {
    public PaymentEvent(Payment payment) {
        super(payment);
    }
}