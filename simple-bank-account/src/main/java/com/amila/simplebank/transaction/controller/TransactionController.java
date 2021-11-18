package com.amila.simplebank.transaction.controller;

import com.amila.simplebank.core.controller.GenericController;
import com.amila.simplebank.transaction.dto.TransactionDTO;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import com.amila.simplebank.transaction.mapper.TransactionEntityMapper;
import com.amila.simplebank.transaction.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(path = "/${spring.application.name}/transaction")
@RestController
public class TransactionController extends GenericController<TransactionDTO, TransactionEntity> {
    public TransactionController(TransactionService transactionService, TransactionEntityMapper transactionEntityMapper) {
        super(transactionService, transactionEntityMapper);
    }

    @Override
    public ResponseEntity<TransactionDTO> update(@RequestBody TransactionDTO model) {
        throw new UnsupportedOperationException("Transaction allow only save entries");
    }

    @Override
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        throw new UnsupportedOperationException("Transaction allow only save entries");
    }
}
