package com.foodordermicroservice.application.service;

import com.foodordermicroservice.application.port.OutboxServicePort;
import com.foodordermicroservice.application.port.PaymentJpaPort;
import com.foodordermicroservice.application.port.PaymentUsecasePort;
import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;
import com.foodordermicroservice.domain.entity.Payment;
import com.foodordermicroservice.domain.event.PaymentEvent;
import com.foodordermicroservice.domain.valueobject.PaymentRequest;
import com.foodordermicroservice.presentation.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentUseCase implements PaymentUsecasePort {
    private final PaymentJpaPort paymentJpaPort;
    private final OutboxServicePort outBoxService;
    @Override
    @Transactional
    public PaymentResponse createPayment(PaymentRequest paymentRequest) {
        Payment payment = Payment.builder()
                .orderId(new OrderId(UUID.fromString(paymentRequest.orderId())))
                .amount(new Money(paymentRequest.amount()))
                .status(PaymentStatus.COMPLETED)
                .build();
        payment.validatePayment(new ArrayList<>());
        payment.initializePayment();
        PaymentResponse paymentResponse = paymentJpaPort.save(payment);
        PaymentEvent paymentEvent = new PaymentEvent(payment);
        outBoxService.saveEvent(paymentEvent);
        return paymentResponse;
    }

    @Override
    public void updatePaymentStatus(String paymentId, String status) {
        paymentJpaPort.updateStatus(paymentId, status);
    }
}
