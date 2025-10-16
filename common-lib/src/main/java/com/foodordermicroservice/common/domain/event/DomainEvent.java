package com.foodordermicroservice.common.domain.event;

public abstract class DomainEvent<T> {
    private final T aggregateRoot;

    protected DomainEvent(T aggregateRoot) {
        this.aggregateRoot = aggregateRoot;
    }

    public T getAggregateRoot() {
        return aggregateRoot;
    }
}
