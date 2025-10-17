package com.foodordermicroservice.application.service;

import com.foodordermicroservice.application.port.PaymentJpaPort;
import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;
import com.foodordermicroservice.common.share.exceptions.BusinessException;
import com.foodordermicroservice.domain.entity.Payment;
import com.foodordermicroservice.infrastructure.persistence.entity.PaymentEntity;
import com.foodordermicroservice.infrastructure.persistence.mapper.PaymentMapper;
import com.foodordermicroservice.infrastructure.persistence.repository.PaymentJpaRepository;
import com.foodordermicroservice.presentation.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentJpaService implements PaymentJpaPort {
    private final PaymentJpaRepository paymentJpaRepository;
    @Override
    public PaymentResponse save(Payment payment) {
        PaymentEntity paymentEntity = paymentJpaRepository.save(PaymentMapper.toPaymentEntity(payment));
        PaymentResponse response = PaymentMapper.fromPaymentEntity(paymentEntity);
        return Optional.ofNullable(response)
                .orElseThrow(() -> new BusinessException("Failed to save payment with id: " + payment.getPaymentId()));
    }

    @Override
    public void updateStatus(String paymentId, String status) {
        try{
            // Validate status
            com.foodordermicroservice.common.share.enums.EntityStatus.valueOf(status);
        } catch (IllegalArgumentException e){
            throw new BusinessException("Invalid status value: " + status);
        }
        try{
            // Validate paymentId as UUID
            java.util.UUID.fromString(paymentId);
        } catch (IllegalArgumentException e){
            throw new BusinessException("Invalid paymentId UUID string: " + paymentId);
        }
        paymentJpaRepository.findById(UUID.fromString(paymentId)).ifPresent(paymentEntity -> {
            paymentEntity.setStatus(PaymentStatus.valueOf(status));
            paymentJpaRepository.save(paymentEntity);
        });
    }

    @Override
    public Optional<PaymentResponse> findById(String paymentId) {
        try{
            // Validate paymentId as UUID
            java.util.UUID.fromString(paymentId);
        } catch (IllegalArgumentException e){
            throw new BusinessException("Invalid paymentId UUID string: " + paymentId);
        }
        return paymentJpaRepository.findById(UUID.fromString(paymentId))
                .map(PaymentMapper::fromPaymentEntity);
    }
}
