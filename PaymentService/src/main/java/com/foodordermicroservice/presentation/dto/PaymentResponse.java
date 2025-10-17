package com.foodordermicroservice.presentation.dto;

import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;
import com.foodordermicroservice.common.share.enums.EntityStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private UUID id;
    private UUID orderId;
    private UUID customerId;
    private PaymentStatus status;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
    private EntityStatus entityStatus;
}
