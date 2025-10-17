package com.foodordermicroservice.orderservice.domain.entity;

import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.OrderId;
import com.foodordermicroservice.common.domain.valueobject.OrderItemId;
import com.foodordermicroservice.common.domain.valueobject.ProductId;


public class OrderItem {
    private OrderItemId orderItemId;
    private final ProductId productId;
    private final String productName;
    private String productDescription;
    private final Money price;
    private final int quantity;
    private Money subTotal;
    private OrderId orderId;

    public void initOrderItem(OrderId orderId, OrderItemId orderItemId) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
    }

    public boolean isPriceValid() {
        return price.isGreaterThanZero() && price.equals(subTotal.multiply(quantity));
    }

    public OrderItemId getOrderItemId() {
        return orderItemId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public static Builder builder() {
        return new Builder();
    }

    private OrderItem(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.subTotal = this.price.multiply(this.quantity);
        this.productDescription = builder.productName;
    }

    public static final class Builder {
        private ProductId productId;
        private String productName;
        private Money price;
        private int quantity;
        private String productDescription;

        private Builder() {
        }

        public static Builder anOrderItem() {
            return new Builder();
        }

        public Builder productId(ProductId productId) {
            this.productId = productId;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder price(Money price) {
            this.price = price;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder productDescription(String productDescription) {
            this.productDescription = productDescription;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
