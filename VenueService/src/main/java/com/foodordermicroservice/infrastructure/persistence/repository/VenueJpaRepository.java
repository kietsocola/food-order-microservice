package com.foodordermicroservice.infrastructure.persistence.repository;

import com.foodordermicroservice.infrastructure.persistence.entity.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VenueJpaRepository extends JpaRepository<VenueEntity, UUID> {
}
