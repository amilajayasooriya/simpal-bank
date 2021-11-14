package com.amila.simplebank.account.service;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.account.repository.AccountRepository;
import com.amila.simplebank.core.service.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("accountService")
public class AccountServiceImpl extends GenericServiceImpl<AccountEntity> implements AccountService {

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
    }
}
