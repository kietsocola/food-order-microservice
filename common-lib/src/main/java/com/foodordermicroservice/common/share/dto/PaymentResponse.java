package com.foodordermicroservice.common.share.dto;

import com.foodordermicroservice.common.domain.valueobject.CustomerId;
import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;
import com.foodordermicroservice.common.share.enums.EntityStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private UUID id;
    private OrderId orderId;
    private CustomerId customerId;
    private Money price;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private EntityStatus entityStatus;
}
