package com.foodordermicroservice.infrastructure.message.rabbitmq.consumer;

import com.foodordermicroservice.application.port.OutboxServicePort;
import com.foodordermicroservice.application.port.PaymentUsecasePort;
import com.foodordermicroservice.domain.event.OrderCreatedEvent;
import com.foodordermicroservice.domain.valueobject.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class EventListener {
    private final PaymentUsecasePort paymentUsecase;

    @Bean(name = "order-created-in")
    @Transactional
    public Consumer<OrderCreatedEvent> orderCreatedIn() {
        return event -> {
            String orderId = String.valueOf(event.getAggregateRoot().getIdValue());
            String customerId = String.valueOf(event.getAggregateRoot().getCustomerId().getValue());
            BigDecimal amount = event.getAggregateRoot().getTotalPrice().getAmount();
            paymentUsecase.createPayment(
                    new PaymentRequest(
                            orderId,
                            customerId,
                            amount
                    )
            );
        };
    }
}
