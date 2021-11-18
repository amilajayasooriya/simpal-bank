package com.amila.simplebank.transaction.repository;

import com.amila.simplebank.core.repository.GenericRepository;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

@Repository("transactionRepository")
public interface TransactionRepository extends GenericRepository<TransactionEntity> {
}
