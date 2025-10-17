package com.foodordermicroservice.orderservice.domain.entity;

import com.foodordermicroservice.common.domain.entity.AggregateRoot;
import com.foodordermicroservice.common.domain.valueobject.*;
import com.foodordermicroservice.common.share.exceptions.BusinessException;
import com.foodordermicroservice.common.utils.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("LombokGetterMayBeUsed")
public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final VenuesId venuesId;
    private final List<OrderItem> orderItems;
    private Money totalPrice;
    private String code;
    private int totalQuantity;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<String> failureMessages;
    private final String address;

    public void validateOrder() {
        validateInitialOrder();
        validateOrderItems();
        validateTotalPrice();
    }
    public void validateInitialOrder() {
        if (this.orderStatus != OrderStatus.PENDING) {
            throw new BusinessException("Order is not in a valid state for initialization.");
        }
        if (this.code == null || this.code.isEmpty()) {
            throw new BusinessException("Order code is not set.");
        }
        if (this.totalQuantity <= 0) {
            throw new BusinessException("Total quantity must be greater than zero.");
        }
    }
    public void validateOrderItems() {
        if (this.orderItems == null || this.orderItems.isEmpty()) {
            throw new BusinessException("Order must contain at least one item.");
        }
        for (OrderItem orderItem : this.orderItems) {
            if (!orderItem.isPriceValid()) {
                throw new BusinessException("Order item price is invalid for product: " + orderItem.getProductName());
            }
        }
    }
    public void validateTotalPrice() {
        Money calculatedTotal = orderItems.stream()
                .map(OrderItem::getSubTotal)
                .reduce(Money.ZERO, Money::add);
        if (!calculatedTotal.equals(this.totalPrice)) {
            throw new BusinessException("Total price does not match sum of order item prices.");
        }
    }
    public void pay() {
        if (this.orderStatus != OrderStatus.PENDING) {
            throw new BusinessException("Order is not in a state that can be paid.");
        }
        this.orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (this.orderStatus != OrderStatus.PAID) {
            throw new BusinessException("Order is not in a state that can be approved.");
        }
        this.orderStatus = OrderStatus.APPROVED;
    }

    public void initOrder() {
        this.orderStatus = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.code = StringUtils.generateRandomString(10);
        this.totalQuantity = orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
        this.totalPrice = orderItems.stream()
                .map(OrderItem::getSubTotal)
                .reduce(Money.ZERO, Money::add);
        initOrderItems();
    }
    private void initOrderItems() {
        for (OrderItem orderItem : orderItems) {
            orderItem.initOrderItem(super.getId(), new OrderItemId(UUID.randomUUID()));
        }
    }

    public void cancel(List<String> failureMessages) {
        if (this.orderStatus != OrderStatus.PAID && this.orderStatus != OrderStatus.PENDING) {
            throw new BusinessException("Order is not in a state that can be cancelled.");
        }
        this.orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (this.failureMessages != null && failureMessages != null) {
            this.failureMessages.addAll(failureMessages);
        }
        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    public OrderId getOrderId() {
        return getId();
    }

    public UUID getIdValue() {
        return getId().getValue();
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
    public VenuesId getVenuesId() {
        return venuesId;
    }
    public Money getTotalPrice() {
        return totalPrice;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public String getCode() {
        return code;
    }
    public int getTotalQuantity() {
        return totalQuantity;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public List<String> getFailureMessages() {
        return failureMessages;
    }
    public String getAddress() {
        return address;
    }
    public static Builder builder() {
        return new Builder();
    }
    private Order(Builder builder) {
        setId(builder.orderId);
        customerId = builder.customerId;
        venuesId = builder.venuesId;
        totalPrice = builder.totalPrice;
        orderItems = builder.orderItems;
        code = builder.code;
        totalQuantity = builder.totalQuantity;
        orderDate = builder.orderDate;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
        address = builder.address;
    }
    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private VenuesId venuesId;
        private Money totalPrice;
        private List<OrderItem> orderItems;
        private String code;
        private int totalQuantity;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private List<String> failureMessages;
        private String address;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder venuesId(VenuesId val) {
            venuesId = val;
            return this;
        }

        public Builder totalPrice(Money val) {
            totalPrice = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            orderItems = val;
            return this;
        }

        public Builder code(String val) {
            code = val;
            return this;
        }

        public Builder totalQuantity(int val) {
            totalQuantity = val;
            return this;
        }

        public Builder orderDate(LocalDateTime val) {
            orderDate = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
