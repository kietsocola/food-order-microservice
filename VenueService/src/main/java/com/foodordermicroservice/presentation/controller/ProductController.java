package com.foodordermicroservice.presentation.controller;

import com.foodordermicroservice.application.port.ProductUseCasePort;
import com.foodordermicroservice.domain.valueobject.ProductRequest;
import com.foodordermicroservice.presentation.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductUseCasePort productUseCasePort;
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productUseCasePort.createProduct(productRequest);
        return ResponseEntity.ok(productResponse);
    }
}
