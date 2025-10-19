package com.foodordermicroservice.application.service;

import com.foodordermicroservice.application.port.ProductUseCasePort;
import com.foodordermicroservice.domain.valueobject.ProductRequest;
import com.foodordermicroservice.infrastructure.persistence.entity.ProductEntity;
import com.foodordermicroservice.infrastructure.persistence.mapper.ProductMapper;
import com.foodordermicroservice.infrastructure.persistence.repository.ProductJpaRepository;
import com.foodordermicroservice.presentation.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductUseCase implements ProductUseCasePort {
    private final ProductJpaRepository productJpaRepository;
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductEntity entity = productJpaRepository.save(ProductMapper.toEntity(productRequest));
        return ProductMapper.toDto(entity);
    }
}
