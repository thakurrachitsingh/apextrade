package com.rachit.apextrade.account.exception;

import com.rachit.apextrade.web.error.ApexTradeException;
import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends ApexTradeException {
    public InsufficientFundsException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "INSUFFICIENT_FUNDS");
    }
}
