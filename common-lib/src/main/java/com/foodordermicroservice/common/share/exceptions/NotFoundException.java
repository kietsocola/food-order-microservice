package com.foodordermicroservice.common.share.exceptions;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message, "NOT_FOUND");
    }

    public NotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: %s", resource, field, value), "NOT_FOUND");
    }
}