package com.amila.simplebank.transaction.exception;

public class InvalidTransactionOperationException extends RuntimeException{
    public InvalidTransactionOperationException() {
    }

    public InvalidTransactionOperationException(String message) {
        super(message);
    }

    public InvalidTransactionOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
