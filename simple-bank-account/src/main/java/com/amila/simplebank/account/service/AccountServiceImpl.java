package com.amila.simplebank.account.service;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.account.repository.AccountRepository;
import com.amila.simplebank.core.service.GenericServiceImpl;
import com.amila.simplebank.transaction.dto.TransactionEntity;
import com.amila.simplebank.transaction.dto.TransactionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("accountService")
@Slf4j
public class AccountServiceImpl extends GenericServiceImpl<AccountEntity> implements AccountService {

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public AccountEntity create(AccountEntity model) {
        TransactionEntity firstTransaction = new TransactionEntity();
        firstTransaction.setTransactionType(TransactionType.Credit);
        firstTransaction.setValueDate(model.getBalanceDate());
        firstTransaction.setCreditAmount(model.getBalance());
        firstTransaction.setAccount(model);

        model.setTransactionList(Collections.singletonList(firstTransaction));
        log.info(String.format("Account %1$s created with default the initial transaction", model.getAccountNumber()));
        return super.create(model);
    }
}
