package com.foodordermicroservice.domain.entity;

import com.foodordermicroservice.common.domain.valueobject.*;

import java.time.LocalDateTime;
import java.util.UUID;

@SuppressWarnings("LombokSetterMayBeUsed")
public class Order {
    private OrderId id;
    private CustomerId customerId;
    private VenuesId venuesId;
    private Money totalPrice;
    private String code;
    private int totalQuantity;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private String address;

    public Order(OrderId id,
                 CustomerId customerId,
                 VenuesId venuesId,
                 Money totalPrice,
                 String code,
                 int totalQuantity,
                 LocalDateTime orderDate,
                 OrderStatus orderStatus,
                 String address) {
        this.id = id;
        this.customerId = customerId;
        this.venuesId = venuesId;
        this.totalPrice = totalPrice;
        this.code = code;
        this.totalQuantity = totalQuantity;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public UUID getIdValue() {
        return this.id.getValue();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
    public void setCustomerId(CustomerId customerId) {
        this.customerId = customerId;
    }
    public VenuesId getVenuesId() {
        return venuesId;
    }
    public void setVenuesId(VenuesId venuesId) {
        this.venuesId = venuesId;
    }
    public Money getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Money totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }
    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
