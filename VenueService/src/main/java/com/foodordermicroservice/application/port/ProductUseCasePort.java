package com.foodordermicroservice.application.port;

import com.foodordermicroservice.domain.valueobject.ProductRequest;
import com.foodordermicroservice.presentation.dto.ProductResponse;

public interface ProductUseCasePort {
    ProductResponse createProduct(ProductRequest productRequest);
}
