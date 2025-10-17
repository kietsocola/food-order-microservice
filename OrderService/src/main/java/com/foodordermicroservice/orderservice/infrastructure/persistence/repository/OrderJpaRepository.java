package com.foodordermicroservice.orderservice.infrastructure.persistence.repository;

import com.foodordermicroservice.orderservice.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
