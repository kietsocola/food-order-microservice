package com.foodordermicroservice.domain.entity;

import com.foodordermicroservice.common.domain.entity.AggregateRoot;
import com.foodordermicroservice.common.domain.valueobject.Money;
import com.foodordermicroservice.common.domain.valueobject.ProductId;
import com.foodordermicroservice.common.domain.valueobject.VenuesId;

public class Product extends AggregateRoot<ProductId> {
    private ProductId id;
    private String name;
    private String description;
    private Money price;
    private VenuesId venueId;

    public Product(ProductId id, String name, String description, Money price, VenuesId venueId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.venueId = venueId;
    }

    public ProductId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }

    public VenuesId getVenueId() {
        return venueId;
    }
}
