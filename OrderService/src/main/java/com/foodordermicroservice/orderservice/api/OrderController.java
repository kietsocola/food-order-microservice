package com.foodordermicroservice.orderservice.api;

import com.foodordermicroservice.orderservice.api.dto.OrderResponse;
import com.foodordermicroservice.orderservice.application.port.OrderRepository;
import com.foodordermicroservice.orderservice.application.port.OrderUseCasePort;
import com.foodordermicroservice.orderservice.domain.valueobject.OrderRequest;
import com.foodordermicroservice.orderservice.infrastructure.persistence.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderUseCasePort orderUseCase;

    @GetMapping("/health")
    public String healthCheck() {
        return "Order Service is up and running!";
    }

//    @GetMapping("/{orderId}")
//    public ResponseEntity<OrderResponse> getOrderStatus(String orderId) {
//        return orderUseCase.findOrderById(orderId).isPresent()
//                ? ResponseEntity.ok(OrderMapper.fromOrder(orderRepository.findOrderById(orderId).get()))
//                : ResponseEntity.notFound().build();
//    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        var order = orderUseCase.createOrder(orderRequest);
        return ResponseEntity.ok(order);
    }
}
