package com.foodordermicroservice.domain.exception;

import com.foodordermicroservice.common.domain.exception.DomainException;

public class PaymentException extends DomainException {
    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }

}
