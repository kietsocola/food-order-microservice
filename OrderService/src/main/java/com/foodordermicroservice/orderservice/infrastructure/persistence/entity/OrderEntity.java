package com.foodordermicroservice.orderservice.infrastructure.persistence.entity;

import com.foodordermicroservice.common.domain.valueobject.OrderStatus;
import com.foodordermicroservice.common.share.entity.AuditableEntity;
import com.foodordermicroservice.common.share.enums.EntityStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String customerId;
    private String venueId;
    private EntityStatus status;
    private BigDecimal totalPrice;
    private int totalQuantity;
    private String code;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private String address;
    @ElementCollection
    private List<String> failureMessages;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;
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
