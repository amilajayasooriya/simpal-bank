package com.amila.simplebank.transaction.controller;

import com.amila.simplebank.core.controller.GenericController;
import com.amila.simplebank.transaction.dto.TransactionEntity;
import com.amila.simplebank.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/${spring.application.name}/transaction")
@RestController
public class TransactionController extends GenericController<TransactionEntity> {

    public TransactionController(TransactionService transactionService) {
        super(transactionService);
    }
}
