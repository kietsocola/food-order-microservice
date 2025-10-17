package com.foodordermicroservice.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodordermicroservice.application.port.OutboxServicePort;
import com.foodordermicroservice.common.infrastructure.outbox.OutboxStatus;
import com.foodordermicroservice.infrastructure.message.outbox.OutBoxRepository;
import com.foodordermicroservice.infrastructure.message.outbox.OutboxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OutboxService implements OutboxServicePort {
    private final OutBoxRepository outBoxRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveEvent(Object event) {
        var outBoxEntity = OutboxEntity.builder()
                .aggregateType("Payment")
                .aggregateId(getAggregateId(event))
                .type(event.getClass().getSimpleName())
                .payload(toJson(event))
                .status(OutboxStatus.STARTED.name())
                .createdAt(Instant.now())
                .build();
        outBoxRepository.save(outBoxEntity);
    }

    private String getAggregateId(Object event) {
        try {
            var aggregate = event.getClass().getMethod("getAggregateRoot").invoke(event);
            return aggregate.getClass().getMethod("getPaymentId").invoke(aggregate).toString();
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
