package com.foodordermicroservice.common.share.exceptions;

public class BaseException extends RuntimeException {
    private String errorCode;
    private Object data;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, String errorCode, Object data) {
        super(message);
        this.errorCode = errorCode;
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getData() {
        return data;
    }
}