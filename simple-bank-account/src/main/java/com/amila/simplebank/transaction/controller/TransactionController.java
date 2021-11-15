package com.amila.simplebank.transaction.controller;

import com.amila.simplebank.core.controller.GenericController;
import com.amila.simplebank.transaction.dto.TransactionEntity;
import com.amila.simplebank.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping(path = "/${spring.application.name}/transaction")
@RestController
public class TransactionController extends GenericController<TransactionEntity> {

    public TransactionController(TransactionService transactionService) {
        super(transactionService);
    }

    @Override
    public ResponseEntity<TransactionEntity> update(TransactionEntity model) {
        throw new UnsupportedOperationException("Transaction allow only save entries");
    }

    @Override
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        throw new UnsupportedOperationException("Transaction allow only save entries");
    }
}
