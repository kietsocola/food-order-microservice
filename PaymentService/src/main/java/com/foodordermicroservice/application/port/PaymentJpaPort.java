package com.foodordermicroservice.application.port;

import com.foodordermicroservice.domain.entity.Payment;
import com.foodordermicroservice.presentation.dto.PaymentResponse;

import java.util.Optional;

public interface PaymentJpaPort {
    PaymentResponse save(Payment payment);
    void updateStatus(String paymentId, String status);
    Optional<PaymentResponse> findById(String paymentId);
}
