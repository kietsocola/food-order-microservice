package com.foodordermicroservice.common.share.exceptions;

public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message, "BUSINESS_ERROR");
    }

    public BusinessException(String message, String errorCode) {
        super(message, errorCode);
    }

    public BusinessException(String message, String errorCode, Object data) {
        super(message, errorCode, data);
    }
}