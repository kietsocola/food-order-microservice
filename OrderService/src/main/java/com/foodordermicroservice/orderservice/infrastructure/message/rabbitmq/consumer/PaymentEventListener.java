package com.foodordermicroservice.orderservice.infrastructure.message.rabbitmq.consumer;

import com.foodordermicroservice.common.domain.valueobject.OrderStatus;
import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;
import com.foodordermicroservice.orderservice.application.port.OrderRepository;
import com.foodordermicroservice.orderservice.application.port.OutBoxServicePort;
import com.foodordermicroservice.orderservice.domain.event.OrderCancelledEvent;
import com.foodordermicroservice.orderservice.domain.event.OrderPaidEvent;
import com.foodordermicroservice.orderservice.domain.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentEventListener {
    private final OrderRepository orderRepository;
    private final OutBoxServicePort outBoxService;

    @Bean
    @Transactional
    public Consumer<PaymentEvent> paymentIn() {
        return event -> {
            String orderId = String.valueOf(event.getAggregateRoot().getOrderId().getValue());
            PaymentStatus status = event.getAggregateRoot().getStatus();

            if (status == PaymentStatus.COMPLETED) {
                orderRepository.updateOrderStatus(orderId, OrderStatus.COMPLETED.name());
                outBoxService.saveOrderEvent(new OrderPaidEvent(orderRepository.findOrderById(orderId).orElseThrow()));
            } else if (status == PaymentStatus.FAILED) {
                orderRepository.updateOrderStatus(orderId, OrderStatus.CANCELLED.name());
                outBoxService.saveOrderEvent(new OrderCancelledEvent(orderRepository.findOrderById(orderId).orElseThrow()));
            }
            log.info("Processed PaymentEvent for Order ID: {}, Payment Status: {}", orderId, status);
        };
    }
}
