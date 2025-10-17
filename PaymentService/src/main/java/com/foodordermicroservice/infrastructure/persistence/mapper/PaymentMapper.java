package com.foodordermicroservice.infrastructure.persistence.mapper;

import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.domain.entity.Payment;
import com.foodordermicroservice.infrastructure.persistence.entity.PaymentEntity;
import com.foodordermicroservice.presentation.dto.PaymentResponse;

public class PaymentMapper {
    public static PaymentEntity toPaymentEntity(Payment payment) {
        if (payment == null) {
            return null;
        }
        return PaymentEntity.builder()
                .id(payment.getPaymentId())
                .orderId(payment.getOrderId().getValue())
                .customerId(payment.getCustomerId().getValue())
                .amount(payment.getAmount().getAmount())
                .status(payment.getStatus())
                .build();
    }

    public static Payment toPayment(PaymentEntity paymentEntity) {
        if (paymentEntity == null) {
            return null;
        }
        return Payment.builder()
                .paymentId(paymentEntity.getId())
                .orderId(new OrderId(paymentEntity.getOrderId()))
                .customerId(new com.foodordermicroservice.common.domain.valueobject.CustomerId(paymentEntity.getCustomerId()))
                .amount(new com.foodordermicroservice.common.domain.valueobject.Money(paymentEntity.getAmount()))
                .status(paymentEntity.getStatus())
                .createdAt(paymentEntity.getCreatedAt())
                .build();
    }

    public static PaymentResponse fromPaymentEntity(PaymentEntity paymentEntity) {
        if (paymentEntity == null) {
            return null;
        }
        return PaymentResponse.builder()
                .id(paymentEntity.getId())
                .orderId(paymentEntity.getOrderId())
                .customerId(paymentEntity.getCustomerId())
                .amount(paymentEntity.getAmount())
                .status(paymentEntity.getStatus())
                .createdAt(paymentEntity.getCreatedAt())
                .build(
        );
    }
}
