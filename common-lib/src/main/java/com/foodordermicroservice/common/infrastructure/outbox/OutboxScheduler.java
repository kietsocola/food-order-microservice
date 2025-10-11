package com.foodordermicroservice.common.infrastructure.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
