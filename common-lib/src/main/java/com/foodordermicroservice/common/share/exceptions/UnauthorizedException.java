package com.foodordermicroservice.common.share.exceptions;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message) {
        super(message, "UNAUTHORIZED");
    }
}