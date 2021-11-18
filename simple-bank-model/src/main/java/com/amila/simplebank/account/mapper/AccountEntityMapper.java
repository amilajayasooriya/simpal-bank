package com.amila.simplebank.account.mapper;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.base.mapper.GenericEntityMapper;
import com.amila.simplebank.transaction.dto.TransactionDTO;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper extends GenericEntityMapper<AccountDTO, AccountEntity> {
    @BeforeMapping
    default void removeParentBeforeEntityToDTO(AccountEntity accountEntity, @MappingTarget AccountDTO accountDTO){
        accountEntity.setUser(null);
    }

    @BeforeMapping
    default void removeParentBeforeEntityToDTO(TransactionEntity transactionEntity, @MappingTarget TransactionDTO transactionDTO){
        transactionEntity.setAccount(null);
    }
}
