package com.amila.simplebank.transaction.dto;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.base.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO extends BaseDTO {
    private LocalDate valueDate;
    private TransactionType transactionType;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private AccountDTO account;
}
