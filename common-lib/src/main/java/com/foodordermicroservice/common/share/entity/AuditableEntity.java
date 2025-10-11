package com.foodordermicroservice.common.share.entity;

import java.time.LocalDateTime;

import com.foodordermicroservice.common.share.enums.EntityStatus;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AuditableEntity extends BaseEntity {
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_by")
    private String deletedBy;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "status")
    private EntityStatus status;

    public void activate(String activateBy) {
        this.status = EntityStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = activateBy;
    }
    public void deactivate(String deactivateBy) {
        this.status = EntityStatus.INACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = deactivateBy;
    }
    public void delete(String deleteBy) {
        this.status = EntityStatus.DELETED;
        this.deletedAt = LocalDateTime.now();
        this.deletedBy = deleteBy;
    }
    public void restore(String restoreBy) {
        this.status = EntityStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = restoreBy;
        this.deletedAt = null;
        this.deletedBy = null;
    }
    public boolean isActive() {
        return this.status == EntityStatus.ACTIVE && !isDeleted();
    }
    public boolean isInactive() {
        return this.status == EntityStatus.INACTIVE;
    }
    public boolean isDeleted() {
        return this.status == EntityStatus.DELETED;
    }
}
