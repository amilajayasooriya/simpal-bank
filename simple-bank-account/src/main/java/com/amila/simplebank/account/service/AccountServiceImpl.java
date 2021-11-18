package com.amila.simplebank.account.service;

import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.account.repository.AccountRepository;
import com.amila.simplebank.core.exception.ServiceException;
import com.amila.simplebank.core.service.GenericServiceImpl;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

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

    @Override
    protected void preValidateUpdate(AccountEntity model) {
        super.preValidateUpdate(model);

        Optional<AccountEntity> accountEntityOptional = findById(model.getId());
        if(accountEntityOptional.isPresent()){
            AccountEntity account = accountEntityOptional.get();

            if(!model.getBalanceDate().isEqual(account.getBalanceDate())){
                String error = String.format("Account number %1$s not allowed to update balance date", model.getAccountNumber());
                log.error(error);
                throw new ServiceException(error);
            }

            if(model.getBalance().compareTo(account.getBalance()) != 0){
                String error = String.format("Account number %1$s not allowed to update balance", model.getAccountNumber());
                log.error(error);
                throw new ServiceException(error);
            }
        } else {
            String error = String.format("Account with id %1$s not existing in database", model.getId());
            log.error(error);
            throw new ServiceException(error);
        }
    }
}
