package com.foodordermicroservice.infrastructure.persistence.mapper;

import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.VenuesId;
import com.foodordermicroservice.domain.valueobject.ProductRequest;
import com.foodordermicroservice.infrastructure.persistence.entity.ProductEntity;

import java.util.UUID;

public class ProductMapper {
    public static ProductEntity toEntity(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }
        return ProductEntity.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .description(productRequest.description())
                .build();
    }

    public static com.foodordermicroservice.presentation.dto.ProductResponse toDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }
        return new com.foodordermicroservice.presentation.dto.ProductResponse(
                productEntity.getId().toString(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getPrice(),
                productEntity.getVenueId().toString()
        );
    }
}
