package com.foodordermicroservice.common.share.dto;

import com.foodordermicroservice.common.domain.valueobject.CustomerId;
import com.foodordermicroservice.common.domain.valueobject.OrderStatus;
import com.foodordermicroservice.common.domain.valueobject.VenuesId;
import com.foodordermicroservice.common.share.enums.EntityStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private UUID id;
    private CustomerId customerId;
    private VenuesId venueId;
    private EntityStatus status;
    private BigDecimal totalPrice;
    private int totalQuantity;
    private String code;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<String> failureMessages;
    private List<OrderItemResponse> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
    private EntityStatus entityStatus;
}
