package com.amila.simplebank.account.dto;

import com.amila.simplebank.base.dto.BaseDTO;
import com.amila.simplebank.transaction.dto.TransactionDTO;
import com.amila.simplebank.user.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AccountDTO extends BaseDTO {
    private String accountNumber;
    private String accountName;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private LocalDate balanceDate;
    private UserDTO user;
    private List<TransactionDTO> transactionList;
}
