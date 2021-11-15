package com.amila.simplebank.transaction.dto;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.base.dto.BaseEntity;
import com.amila.simplebank.transaction.exception.InvalidTransactionOperationException;
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

    @Column(nullable = false, updatable = false)
    private TransactionType transactionType;

    private BigDecimal debitAmount;
    private BigDecimal creditAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    @JsonBackReference("account_transaction")
    private AccountEntity account;

    @PreUpdate
    @PreRemove
    private void updateAccountBalance(){
        throw new InvalidTransactionOperationException("Transactions are not allowed to Edit or Delete");
    }
}
