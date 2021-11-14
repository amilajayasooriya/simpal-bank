package com.amila.simplebank.transaction.dto;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.base.dto.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
public class TransactionEntity extends BaseEntity {
    @Column(nullable = false)
    private LocalDate valueDate;

    @Column(nullable = false)
    private TransactionType transactionType;

    private BigDecimal debitAccount;
    private BigDecimal creditAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    @JsonBackReference("account_transaction")
    private AccountEntity account;
}
