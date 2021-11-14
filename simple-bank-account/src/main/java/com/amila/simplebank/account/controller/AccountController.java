package com.amila.simplebank.account.controller;

import com.amila.simplebank.account.dto.AccountEntity;
import com.amila.simplebank.account.service.AccountService;
import com.amila.simplebank.core.controller.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/${spring.application.name}/account")
@RestController
public class AccountController extends GenericController<AccountEntity> {

    public AccountController(AccountService accountService) {
        super(accountService);
    }
}
