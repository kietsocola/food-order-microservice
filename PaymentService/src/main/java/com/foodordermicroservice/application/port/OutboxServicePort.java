package com.foodordermicroservice.application.port;

public interface OutboxServicePort {
    void saveEvent(Object event);
}
