package com.foodordermicroservice.domain.entity;

import com.foodordermicroservice.common.domain.entity.AggregateRoot;
import com.foodordermicroservice.common.domain.valueobject.CustomerId;
import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Payment extends AggregateRoot<UUID> {
    private UUID paymentId;
    private OrderId orderId;
    private CustomerId customerId;
    private PaymentStatus status;
    private Money amount;
    private LocalDateTime createdAt;

    public Payment(UUID paymentId, OrderId orderId, CustomerId customerId, PaymentStatus status, Money amount, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public void initializePayment() {
        this.paymentId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.status = PaymentStatus.COMPLETED;
    }

    public void validatePayment(List<String> errors) {
        if (this.amount == null || this.amount.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Payment amount must be greater than zero.");
        }
        if (this.customerId == null) {
            errors.add("Customer ID cannot be null.");
        }
        if (this.orderId == null) {
            errors.add("Order ID cannot be null.");
        }
    }

    public void updateStatus(PaymentStatus newStatus) {
        this.status = newStatus;
    }

    // Getters and Setters
    public UUID getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }
    public OrderId getOrderId() {
        return orderId;
    }
    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }
    public CustomerId getCustomerId() {
        return customerId;
    }
    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.orderId = builder.orderId;
        this.customerId = builder.customerId;
        this.status = builder.status;
        this.amount = builder.amount;
        this.createdAt = builder.createdAt;
    }

    public static final class Builder {
        private UUID paymentId;
        private OrderId orderId;
        private CustomerId customerId;
        private PaymentStatus status;
        private Money amount;
        private LocalDateTime createdAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder paymentId(UUID paymentId) {
            this.paymentId = paymentId;
            return this;
        }
        public Builder orderId(OrderId orderId) {
            this.orderId = orderId;
            return this;
        }
        public Builder customerId(CustomerId customerId) {
            this.customerId = customerId;
            return this;
        }
        public Builder status(PaymentStatus status) {
            this.status = status;
            return this;
        }
        public Builder amount(Money amount) {
            this.amount = amount;
            return this;
        }
        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
