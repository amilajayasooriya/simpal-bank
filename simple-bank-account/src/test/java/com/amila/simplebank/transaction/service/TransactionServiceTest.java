package com.amila.simplebank.transaction.service;

import com.amila.simplebank.account.dto.AccountType;
import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.account.service.AccountService;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import com.amila.simplebank.transaction.exception.InvalidTransactionOperationException;
import com.amila.simplebank.user.entity.UserEntity;
import com.amila.simplebank.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class TransactionServiceTest {
    private AccountService accountService;
    private UserService userService;
    private TransactionService transactionService;

    private TransactionEntity transactionEntity;
    private AccountEntity accountEntity;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @BeforeEach
    void beforeEach() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Martin Anderson");
        userEntity.setEmail("martin.anderson@gmail.com");
        userEntity.setNin("67554373D12");
        userEntity = userService.createRecord(userEntity);

        accountEntity = new AccountEntity();
        accountEntity.setAccountName("Martin Anderson");
        accountEntity.setAccountNumber("109343842234");
        accountEntity.setAccountType(AccountType.SAVINGS);
        accountEntity.setBalance(BigDecimal.valueOf(550.50));
        accountEntity.setBalanceDate(LocalDate.now());
        accountEntity.setCurrency("AUD");
        accountEntity.setTransactionList(Collections.emptyList());
        accountEntity.setUser(userEntity);
        accountEntity = accountService.createRecord(accountEntity);

        transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionType(TransactionType.Credit);
        transactionEntity.setValueDate(LocalDate.now());
        transactionEntity.setCreditAmount(BigDecimal.valueOf(600));
        transactionEntity.setAccount(accountEntity);
    }

    @Test
    @DisplayName("Test transaction create via service layer")
    void createServiceTest() {
        TransactionEntity transactionEntityCreated = transactionService.createRecord(transactionEntity);
        assertNotNull(transactionEntityCreated);
    }

    @Test
    @DisplayName("Test transaction ID generated via service layer")
    void createServiceIDAutoGenTest() {
        TransactionEntity transactionEntityCreated = transactionService.createRecord(transactionEntity);
        assertNotNull(transactionEntityCreated.getId());
    }

    @Test
    @DisplayName("Test Negative update Transaction")
    void updateServiceTransactionNegativeTest() {
        TransactionEntity transactionEntityCreated = transactionService.createRecord(transactionEntity);
        transactionEntityCreated.setValueDate(LocalDate.now().minusDays(10));

        InvalidTransactionOperationException thrown = assertThrows(
                InvalidTransactionOperationException.class,
                () -> transactionService.updateRecord(transactionEntityCreated));
    }
}
