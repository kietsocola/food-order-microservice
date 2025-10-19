package com.foodordermicroservice.infrastructure.message.rabbitmq.consumer;

import com.foodordermicroservice.application.port.OutboxServicePort;
import com.foodordermicroservice.domain.event.DeliveryEvent;
import com.foodordermicroservice.domain.event.OrderPaidEvent;
import com.foodordermicroservice.presentation.dto.OrderPaidEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class EventListener {
    private final OutboxServicePort outBoxService;
    @Bean
    @Transactional
    public Consumer<OrderPaidEventPayload> orderPaidIn() {
        return event -> {
            // Handle order paid event
            DeliveryEvent deliveryEvent = new DeliveryEvent(
                    event.getAggregateRoot().getOrderId().getValue(),
                    event.getAggregateRoot().getCustomerId().getValue(),
                    event.getAggregateRoot().getAddress(),
                    event.getAggregateRoot().getOrderStatus(),
                    null,
                    null,
                    LocalDateTime.now()
            );
            outBoxService.saveEvent(deliveryEvent, event.getAggregateRoot().getOrderId().getValue());
        };
    }

    @Bean(name = "location-in")
    public Consumer<DeliveryEvent> locationIn(SimpMessagingTemplate messagingTemplate) {
        return event -> {
            String orderId = String.valueOf(event.orderId());
            String topic = "/topic/delivery." + orderId;
            messagingTemplate.convertAndSend(topic, event);
        };
    }
}
