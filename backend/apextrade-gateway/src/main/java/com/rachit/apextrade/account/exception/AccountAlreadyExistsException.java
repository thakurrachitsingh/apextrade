package com.rachit.apextrade.account.exception;

import com.rachit.apextrade.web.error.ApexTradeException;
import org.springframework.http.HttpStatus;

public class AccountAlreadyExistsException extends ApexTradeException {
    public AccountAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT, "ACCOUNT_ALREADY_EXISTS");
    }
}

