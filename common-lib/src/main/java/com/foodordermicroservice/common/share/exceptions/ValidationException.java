package com.foodordermicroservice.common.share.exceptions;

public class ValidationException extends BaseException {
    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }

    public ValidationException(String message, Object data) {
        super(message, "VALIDATION_ERROR", data);
    }
}