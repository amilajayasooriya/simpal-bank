package com.amila.simplebank.transaction.service;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.account.service.AccountService;
import com.amila.simplebank.core.service.GenericServiceImpl;
import com.amila.simplebank.transaction.dto.TransactionEntity;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("transactionService")
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
        
        if(accountEntityOptional.isPresent()) {
            AccountEntity account = accountEntityOptional.get();
            if (model.getTransactionType() == TransactionType.Credit) {
                model.getAccount().setBalance(account.getBalance().add(model.getCreditAccount()));
            } else {
                model.getAccount().setBalance(account.getBalance().subtract(model.getDebitAccount()));
            }
            model.setAccount(account);
            // update the accout also
            return super.create(model);
        } else {
            //amila todo thrawexception
            return null;
        }
    }
}
