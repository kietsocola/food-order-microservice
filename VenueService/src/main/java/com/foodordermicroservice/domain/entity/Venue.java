package com.foodordermicroservice.domain.entity;

import com.foodordermicroservice.common.domain.entity.AggregateRoot;
import com.foodordermicroservice.common.domain.valueobject.VenuesId;

import java.util.List;

public class Venue extends AggregateRoot<VenuesId> {
    private VenuesId id;
    private String name;
    private String location;
    private List<Product> products;

    public Venue(VenuesId id, String name, String location, List<Product> products) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.products = products;
    }
    public VenuesId getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public List<Product> getProducts() {
        return products;
    }
}
