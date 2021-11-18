package com.amila.simplebank.account.controller;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.account.mapper.AccountEntityMapper;
import com.amila.simplebank.account.service.AccountService;
import com.amila.simplebank.core.controller.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/${spring.application.name}/account")
@RestController
public class AccountController extends GenericController<AccountDTO, AccountEntity> {

    public AccountController(AccountService accountService, AccountEntityMapper accountEntityMapper) {
        super(accountService, accountEntityMapper);
    }
}
