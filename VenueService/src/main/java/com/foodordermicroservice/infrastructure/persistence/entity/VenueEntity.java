package com.foodordermicroservice.infrastructure.persistence.entity;

import com.foodordermicroservice.common.domain.valueobject.VenuesId;
import com.foodordermicroservice.common.share.entity.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venues")
public class VenueEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String location;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "venue_id")
    private List<ProductEntity> products;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VenueEntity that)) return false;
        return id != null && id.equals(that.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
