package com.amila.simplebank.account.dto;

import com.amila.simplebank.base.dto.BaseEntity;
import com.amila.simplebank.transaction.dto.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
public class AccountEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false, unique = true)
    private String accountName;

    @Column(nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private LocalDate balanceDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("account_transaction")
    private List<TransactionEntity> transactionList;
}
