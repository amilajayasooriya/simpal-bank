package com.amila.simplebank.account.exception;

public class AccountNotExistException extends RuntimeException{
    public AccountNotExistException() {
    }

    public AccountNotExistException(String message) {
        super(message);
    }
}
