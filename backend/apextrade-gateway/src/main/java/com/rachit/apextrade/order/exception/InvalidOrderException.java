package com.rachit.apextrade.order.exception;

import com.rachit.apextrade.web.error.ApexTradeException;
import org.springframework.http.HttpStatus;

public class InvalidOrderException extends ApexTradeException {
    public InvalidOrderException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "INVALID_ORDER");
    }
}
