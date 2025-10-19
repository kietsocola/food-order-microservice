package com.foodordermicroservice.presentation.controller;

import com.foodordermicroservice.application.port.OutboxServicePort;
import com.foodordermicroservice.domain.event.DeliveryEvent;
import com.foodordermicroservice.presentation.dto.LocationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class DeliveryController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/delivery")
    public void receiveLocation(@Payload LocationMessage message) {
        // broadcast tới client đang subscribe
        messagingTemplate.convertAndSend("/topic/delivery." + message.orderId(), message);
    }
}
