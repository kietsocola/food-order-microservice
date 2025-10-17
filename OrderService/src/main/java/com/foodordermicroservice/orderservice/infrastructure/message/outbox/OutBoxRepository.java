package com.foodordermicroservice.orderservice.infrastructure.message.outbox;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBoxEntity, UUID> {
    List<OutBoxEntity> findByStatus(String status);
}
