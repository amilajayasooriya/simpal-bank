package com.amila.simplebank.account.repository;

import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.core.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository("accountRepository")
public interface AccountRepository extends GenericRepository<AccountEntity> {
}
