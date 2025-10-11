package com.foodordermicroservice.common.share.exceptions;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message) {
        super(message, "FORBIDDEN");
    }
}