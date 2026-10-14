package com.rachit.apextrade.order.exception;

import com.rachit.apextrade.web.error.ApexTradeException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ApexTradeException {
    public OrderNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "ORDER_NOT_FOUND");
    }
}
