package com.amila.simplebank.account.entity;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.account.dto.AccountType;
import com.amila.simplebank.account.mapper.AccountEntityMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountEntityConversionTest {
    private AccountEntityMapper accountEntityMapper;

    @Autowired
    public void setAccountEntityMapper(AccountEntityMapper accountEntityMapper) {
        this.accountEntityMapper = accountEntityMapper;
    }

    @Test
    @DisplayName("User Entity to DTO test")
    void dtoToEntityTest(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName("Martin Anderson");
        accountEntity.setAccountNumber("109343842234");
        accountEntity.setAccountType(AccountType.SAVINGS);
        accountEntity.setBalance(BigDecimal.valueOf(550.50));
        accountEntity.setBalanceDate(LocalDate.now());
        accountEntity.setCurrency("AUD");
        accountEntity.setTransactionList(Collections.emptyList());

        AccountDTO accountDTO = accountEntityMapper.toDto(accountEntity);
        assertTrue(isAllPropertiesEqual(accountEntity, accountDTO));
    }

    @Test
    @DisplayName("User DTO to Entity test")
    void entityToDtoTest(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountName("Martin Anderson");
        accountDTO.setAccountNumber("109343842234");
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setBalance(BigDecimal.valueOf(550.50));
        accountDTO.setBalanceDate(LocalDate.now());
        accountDTO.setCurrency("AUD");
        accountDTO.setTransactionList(Collections.emptyList());

        AccountEntity accountEntity = accountEntityMapper.toEntity(accountDTO);
        assertTrue(isAllPropertiesEqual(accountEntity, accountDTO));
    }

    private boolean isAllPropertiesEqual(AccountEntity a, AccountDTO b) {
        return a.getAccountName().equals(b.getAccountName()) &&
                a.getAccountNumber().equals(b.getAccountNumber()) &&
                a.getAccountType().equals(b.getAccountType()) &&
                a.getBalance().compareTo(b.getBalance()) == 0 &&
                a.getBalanceDate().isEqual(b.getBalanceDate()) &&
                a.getCurrency().equals(b.getCurrency());
    }
}
