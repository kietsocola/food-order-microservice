package com.foodordermicroservice.common.domain.valueobject;

public abstract class BaseId<T> {
    private final T value;

    protected BaseId(T value) {
        if (value == null) {
            throw new IllegalArgumentException("ID value cannot be null");
        }
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) obj;
        return value.equals(baseId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
