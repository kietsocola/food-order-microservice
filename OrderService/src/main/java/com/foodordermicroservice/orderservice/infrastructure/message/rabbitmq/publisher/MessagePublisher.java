package com.foodordermicroservice.orderservice.infrastructure.message.rabbitmq.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessagePublisher {
    private final StreamBridge streamBridge;

    public void publish(String eventType, Object payload) {
        log.info("Publishing event type={} to order-out", eventType);
        streamBridge.send("order-out", MessageBuilder.withPayload(payload)
                .setHeader("type", eventType)
                .build());
    }
}
