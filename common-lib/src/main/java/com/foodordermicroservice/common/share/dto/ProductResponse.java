package com.foodordermicroservice.common.share.dto;

import com.foodordermicroservice.common.domain.valueobject.ProductId;
import com.foodordermicroservice.common.domain.valueobject.VenuesId;
import com.foodordermicroservice.common.share.enums.EntityStatus;

public class ProductResponse {
    private ProductId productId;
    private String productName;
    private String productDescription;
    private String productPrice;
    private String productImage;
    private String productCategory;
    private VenuesId venueId;
    private int availableQuantity;
    private boolean available;
    private String unit;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String updatedBy;
    private EntityStatus entityStatus;
}
