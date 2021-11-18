package com.amila.simplebank.transaction.service;

import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.account.exception.AccountNotExistException;
import com.amila.simplebank.account.service.AccountService;
import com.amila.simplebank.core.service.GenericServiceImpl;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("transactionService")
@Slf4j
public class TransactionServiceImpl extends GenericServiceImpl<TransactionEntity> implements TransactionService {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }

    @Override
    public TransactionEntity create(TransactionEntity model) {
        Optional<AccountEntity> accountEntityOptional = accountService.findById(model.getAccount().getId());

        if (accountEntityOptional.isPresent()) {
            AccountEntity account = accountEntityOptional.get();
            if (model.getTransactionType() == TransactionType.Credit) {
                account.setBalance(account.getBalance().add(model.getCreditAmount()));
            } else if (model.getTransactionType() == TransactionType.Debit) {
                account.setBalance(account.getBalance().subtract(model.getDebitAmount()));
            }

            account.setBalanceDate(model.getValueDate());
            model.setAccount(account);
            log.info(String.format("Transaction creating for account %1$s", account.getAccountNumber()));
            return super.create(model);
        } else {
            String error = String.format("Account with id %1$s not exists", model.getAccount().getId());
            log.error(error);
            throw new AccountNotExistException(error);
        }
    }
}
