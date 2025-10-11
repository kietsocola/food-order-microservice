package com.foodordermicroservice.common.infrastructure.saga;

public interface SagaStep {
    void process();
    void rollback();
}
