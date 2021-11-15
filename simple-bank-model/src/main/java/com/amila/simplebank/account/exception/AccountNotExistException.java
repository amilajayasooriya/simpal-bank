package com.amila.simplebank.account.exception;

public class AccountNotExistException extends Exception{
    public AccountNotExistException() {
    }

    public AccountNotExistException(String message) {
        super(message);
    }
}
