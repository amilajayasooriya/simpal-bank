package com.amila.simplebank.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionType {
    Credit("CREDIT"),
    Debit("DEBIT");

    private String value;
}
