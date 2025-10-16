package com.foodordermicroservice.orderservice.infrastructure.message.rabbitmq.publisher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodordermicroservice.common.domain.valueobject.OrderStatus;
import com.foodordermicroservice.orderservice.application.port.OrderEventPublisher;
import com.foodordermicroservice.orderservice.application.port.OutBoxServicePort;
import com.foodordermicroservice.orderservice.infrastructure.message.outbox.OutBoxEntity;
import com.foodordermicroservice.orderservice.infrastructure.message.outbox.OutBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OutboxService implements OutBoxServicePort {
    private final OutBoxRepository outBoxRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveOrderEvent(Object event) {
        var outBoxEntity = OutBoxEntity.builder()
                .aggregateType("Order")
                .aggregateId(getAggregateId(event))
                .type(event.getClass().getSimpleName())
                .payload(toJson(event))
                .status(OrderStatus.PENDING.name())
                .createdAt(Instant.now())
                .build();
        outBoxRepository.save(outBoxEntity);
    }

    private String getAggregateId(Object event) {
        // Giả sử tất cả event có method getAggregate()
        try {
            var aggregate = event.getClass().getMethod("getAggregate").invoke(event);
            return aggregate.getClass().getMethod("getId").invoke(aggregate).toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
