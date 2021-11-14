package com.amila.simplebank.account.dto;

import com.amila.simplebank.base.dto.BaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNT")
@Getter
public class AccountEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    String accountNumber;

    @Column(nullable = false, unique = true)
    String accountName;

    @Column(nullable = false)
    AccountType accountType;

    @Column(nullable = false)
    BigDecimal balance;

    @Column(nullable = false)
    String currency;

    @Column(nullable = false)
    LocalDate balanceDate;
}
