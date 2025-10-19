package com.foodordermicroservice.infrastructure.schedule;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodordermicroservice.common.infrastructure.outbox.OutboxScheduler;
import com.foodordermicroservice.common.infrastructure.outbox.OutboxStatus;
import com.foodordermicroservice.infrastructure.message.outbox.OutBoxRepository;
import com.foodordermicroservice.infrastructure.message.outbox.OutboxEntity;
import com.foodordermicroservice.infrastructure.message.rabbitmq.publisher.MessagePublisher;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxSchedulerImpl implements OutboxScheduler {
    private final OutBoxRepository outboxRepository;
    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 5000)
    @Transactional
    @Override
    public void processOutboxMessage() {
        List<OutboxEntity> pendingMessages = outboxRepository.findByStatus(OutboxStatus.STARTED.name());
        for (OutboxEntity outbox : pendingMessages) {
            try {
                log.info("OutboxScheduler triggered... found {} pending messages", pendingMessages.size());
                Object payload = objectMapper.readValue(outbox.getPayload(), Object.class);

                publisher.publish(payload);

                outbox.setStatus(OutboxStatus.COMPLETED.name());
                outbox.setCreatedAt(Instant.now());
                outboxRepository.save(outbox);
            } catch (Exception e) {
                outbox.setStatus(OutboxStatus.FAILED.name());
                outboxRepository.save(outbox);
            }
        }
    }
}
