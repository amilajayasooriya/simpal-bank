package com.amila.simplebank.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountType {
    SAVINGS("Savings"),
    CURRENT("Current");

    private final String value;
}
