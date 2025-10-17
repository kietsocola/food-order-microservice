package com.foodordermicroservice.orderservice.application.port;

public interface OutBoxServicePort {
    void saveOrderEvent(Object event);
}
