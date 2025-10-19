package com.foodordermicroservice.presentation.dto;

import com.foodordermicroservice.common.domain.valueobject.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderPaidEventPayload {
    private AggregateRoot aggregateRoot;

    @Data
    public static class AggregateRoot {
        private Id orderId;
        private CustomerId customerId;
        private String address;
        private OrderStatus orderStatus;

        @Data
        public static class Id {
            private String value;
        }

        @Data
        public static class CustomerId {
            private String value;
        }

        @Data
        public static class TotalPrice {
            private BigDecimal amount;
        }
    }
}
