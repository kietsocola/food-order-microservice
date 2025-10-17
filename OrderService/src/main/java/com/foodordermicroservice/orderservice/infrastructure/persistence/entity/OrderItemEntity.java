package com.foodordermicroservice.orderservice.infrastructure.persistence.entity;

import com.foodordermicroservice.common.share.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItemEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    private String productId;
    private String productName;
    private String productDescription;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subTotal;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return id != null && id.equals(that.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
