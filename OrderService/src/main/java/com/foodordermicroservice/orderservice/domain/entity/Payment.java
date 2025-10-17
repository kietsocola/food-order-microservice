package com.foodordermicroservice.orderservice.domain.entity;

import com.foodordermicroservice.common.domain.valueobject.CustomerId;
import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.common.domain.valueobject.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {
    private String paymentId;
    private OrderId orderId;
    private CustomerId customerId;
    private PaymentStatus status;
    private Money amount;
    private LocalDateTime createdAt;

    public Payment(String paymentId, OrderId orderId, CustomerId customerId, PaymentStatus status, Money amount, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
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
}
