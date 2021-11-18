package com.amila.simplebank.transaction.mapper;

import com.amila.simplebank.base.mapper.GenericEntityMapper;
import com.amila.simplebank.transaction.dto.TransactionDTO;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionEntityMapper extends GenericEntityMapper<TransactionDTO, TransactionEntity> {

    @BeforeMapping
    default void removeParentBeforeEntityToDTO(TransactionEntity transactionEntity, @MappingTarget TransactionDTO transactionDTO){
        transactionEntity.setAccount(null);
    }
}
