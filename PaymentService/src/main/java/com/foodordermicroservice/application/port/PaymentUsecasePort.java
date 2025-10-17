package com.foodordermicroservice.application.port;

import com.foodordermicroservice.domain.valueobject.PaymentRequest;
import com.foodordermicroservice.presentation.dto.PaymentResponse;

import java.math.BigDecimal;

public interface PaymentUsecasePort {
    PaymentResponse createPayment(PaymentRequest paymentRequest);
    void updatePaymentStatus(String paymentId, String status);
}
