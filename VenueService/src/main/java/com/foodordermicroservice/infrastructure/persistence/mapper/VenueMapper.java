package com.foodordermicroservice.infrastructure.persistence.mapper;

import com.foodordermicroservice.domain.valueobject.VenueRequest;
import com.foodordermicroservice.infrastructure.persistence.entity.VenueEntity;
import com.foodordermicroservice.presentation.dto.VenueResponse;

public class VenueMapper {
    public static VenueEntity toEntity(VenueRequest venueRequest) {
        if (venueRequest == null) {
            return null;
        }
        VenueEntity entity = VenueEntity.builder()
                .name(venueRequest.venueName())
                .location(venueRequest.venueAddress())
                .build();
        if(venueRequest.products() != null) {
            entity.setProducts(venueRequest.products().stream()
                    .map(ProductMapper::toEntity)
                    .toList());
        }
        return entity;
    }

    public static VenueResponse toDto(VenueEntity venueEntity) {
        if (venueEntity == null) {
            return null;
        }
        return new VenueResponse(
                venueEntity.getId().toString(),
                venueEntity.getName(),
                venueEntity.getLocation(),
                venueEntity.getProducts() != null ? venueEntity.getProducts().stream()
                        .map(ProductMapper::toDto)
                        .toList() : null
        );
    }
}
