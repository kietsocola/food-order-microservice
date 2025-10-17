package com.foodordermicroservice.orderservice.application.service;

import com.foodordermicroservice.orderservice.api.dto.OrderResponse;
import com.foodordermicroservice.orderservice.application.port.OrderRepository;
import com.foodordermicroservice.orderservice.application.port.OrderUseCasePort;
import com.foodordermicroservice.orderservice.application.port.OutBoxServicePort;
import com.foodordermicroservice.orderservice.domain.entity.Order;
import com.foodordermicroservice.orderservice.domain.event.OrderCreatedEvent;
import com.foodordermicroservice.orderservice.domain.valueobject.OrderRequest;
//import com.foodordermicroservice.orderservice.infrastructure.client.CustomerClient;
//import com.foodordermicroservice.orderservice.infrastructure.client.VenueClient;
import com.foodordermicroservice.orderservice.infrastructure.persistence.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderUseCase implements OrderUseCasePort {
    private final OrderRepository orderRepository;
//    private final CustomerClient customerClient;
//    private final VenueClient venueClient;
    private final OutBoxServicePort outBoxService;

    @Override
    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
//        if(!customerClient.checkCustomerExists(orderRequest.customerId())){
//            throw new IllegalArgumentException("Customer with ID " + orderRequest.customerId() + " does not exist.");
//        }
//        if(!venueClient.checkVenueExists(orderRequest.venuesId())){
//            throw new IllegalArgumentException("Venue with ID " + orderRequest.venuesId() + " does not exist.");
//        }
        Order order = OrderMapper.toOrderDomain(orderRequest);
        order.initOrder();
        Order savedOrder = orderRepository.save(order);
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(savedOrder);
        outBoxService.saveOrderEvent(orderCreatedEvent);
        return OrderMapper.fromOrder(savedOrder);
    }

    @Override
    public void updateOrderStatus(String orderId, String status) {
        orderRepository.updateOrderStatus(orderId, status);
    }
}
