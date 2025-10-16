package com.foodordermicroservice.common.share.dto;

import com.foodordermicroservice.common.domain.valueobject.ProductId;
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
public class OrderItemResponse {
    private UUID id;
    private ProductId productId;
    private String productName;
    private String productDescription;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime deletedAt;
    private String deletedBy;
    private EntityStatus entityStatus;
}
