package com.amila.simplebank.transaction.entity;

import com.amila.simplebank.transaction.dto.TransactionDTO;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.mapper.TransactionEntityMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TransactionEntityConversionTest {
    private TransactionEntityMapper transactionEntityMapper;

    @Autowired
    public void setTransactionEntityMapper(TransactionEntityMapper transactionEntityMapper) {
        this.transactionEntityMapper = transactionEntityMapper;
    }

    @Test
    @DisplayName("User Entity to DTO test")
    void dtoToEntityTest(){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionType(TransactionType.Credit);
        transactionEntity.setCreditAmount(BigDecimal.valueOf(500));
        transactionEntity.setValueDate(LocalDate.now());

        TransactionDTO accountDTO = transactionEntityMapper.toDto(transactionEntity);
        assertTrue(isAllPropertiesEqual(transactionEntity, accountDTO));
    }

    @Test
    @DisplayName("User DTO to Entity test")
    void entityToDtoTest(){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionType(TransactionType.Credit);
        transactionDTO.setCreditAmount(BigDecimal.valueOf(500));
        transactionDTO.setValueDate(LocalDate.now());

        TransactionEntity transactionEntity = transactionEntityMapper.toEntity(transactionDTO);
        assertTrue(isAllPropertiesEqual(transactionEntity, transactionDTO));
    }

    private boolean isAllPropertiesEqual(TransactionEntity a, TransactionDTO b) {
        if (a.getTransactionType().equals(b.getTransactionType()) &&
                a.getValueDate().equals(b.getValueDate())){
            return a.getTransactionType() == TransactionType.Credit && a.getCreditAmount().compareTo(b.getCreditAmount()) == 0
                    || a.getTransactionType() == TransactionType.Debit && a.getDebitAmount().compareTo(b.getDebitAmount()) == 0;
        }

        return false;
    }
}
