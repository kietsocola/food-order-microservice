package com.foodordermicroservice.orderservice.infrastructure.persistence.repository;

import com.foodordermicroservice.common.share.enums.EntityStatus;
import com.foodordermicroservice.orderservice.application.port.OrderRepository;
import com.foodordermicroservice.orderservice.domain.entity.Order;
import com.foodordermicroservice.orderservice.infrastructure.persistence.mapper.OrderMapper;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Optional<Order> findOrderById(String orderId) {
        UUID orderUUID;
        try {
            orderUUID = UUID.fromString(orderId);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid orderId UUID string: " + orderId);
        }
        return orderJpaRepository.findById(orderUUID).map(OrderMapper::toOrderDomain);
    }

    @Override
    public Order save(Order order) {
        if (order == null) {
            throw new ValidationException("Order cannot be null");
        }
        var orderEntity = OrderMapper.toOrderEntity(order);
        var savedEntity = orderJpaRepository.save(orderEntity);
        return OrderMapper.toOrderDomain(savedEntity);
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        UUID orderUUID;
        try {
            orderUUID = UUID.fromString(orderId);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid orderId UUID string: " + orderId);
        }
        try {
            EntityStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid status value: " + status);
        }
        orderJpaRepository.findById(orderUUID).ifPresent(orderEntity -> {
            orderEntity.setStatus(EntityStatus.valueOf(status));
            orderJpaRepository.save(orderEntity);
        });
    }
}
