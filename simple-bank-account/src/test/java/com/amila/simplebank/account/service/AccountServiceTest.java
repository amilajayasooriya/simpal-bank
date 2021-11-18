package com.amila.simplebank.account.service;

import com.amila.simplebank.account.dto.AccountType;
import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.core.exception.ServiceException;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import com.amila.simplebank.user.entity.UserEntity;
import com.amila.simplebank.user.service.UserService;
import org.junit.jupiter.api.BeforeAll;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class AccountServiceTest {
    private AccountService accountService;
    private UserService userService;

    private AccountEntity accountEntity;

    @BeforeAll
    static void beforeAll() {

    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
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
    }

    @Test
    @DisplayName("Test user create via service layer")
    void createServiceTest() {
        AccountEntity createdAccount = accountService.createRecord(accountEntity);
        assertNotNull(createdAccount);
    }

    @Test
    @DisplayName("Test user ID generated via service layer")
    void createServiceIDAutoGenTest() {
        AccountEntity createdAccount = accountService.createRecord(accountEntity);
        assertNotNull(createdAccount.getId());
    }

    @Test
    @DisplayName("Test user Update via service layer")
    void updateServiceIDAutoGenTest() {
        String newName = "Thomas";
        AccountEntity accountEntitySaved = accountService.createRecord(accountEntity);
        accountEntitySaved.setAccountName(newName);
        accountService.updateRecord(accountEntitySaved);

        Optional<AccountEntity> fromDbOptional = accountService.findById(accountEntity.getId());

        assertEquals(fromDbOptional.get().getAccountName(), newName);
    }

    @Test
    @DisplayName("Test initial transaction created")
    void testInitialTransaction() {
        AccountEntity createdAccount = accountService.createRecord(accountEntity);
        assertEquals(1, createdAccount.getTransactionList().size());
    }

    @Test
    @DisplayName("Test initial transaction Amount")
    void testInitialTransactionAmount() {
        AccountEntity createdAccount = accountService.createRecord(accountEntity);
        List<TransactionEntity> transactionEntityList = createdAccount.getTransactionList();

        assertEquals(transactionEntityList.get(0).getCreditAmount(), accountEntity.getBalance());
    }

    @Test
    @DisplayName("Test initial transaction Amount")
    void testInitialTransactionType() {
        AccountEntity createdAccount = accountService.createRecord(accountEntity);
        List<TransactionEntity> transactionEntityList = createdAccount.getTransactionList();

        assertEquals(TransactionType.Credit, transactionEntityList.get(0).getTransactionType());
    }

    @Test
    @DisplayName("Test transaction Amount update NegativeTest")
    void testUpdateBalanceNegativeUpdateBalance() {
        AccountEntity accountEntitySaved = accountService.createRecord(accountEntity);
        accountEntitySaved.setBalance(BigDecimal.valueOf(600));

        ServiceException thrown = assertThrows(
                ServiceException.class,
                () -> accountService.updateRecord(accountEntitySaved));
    }

    @Test
    @DisplayName("Test transaction Date update NegativeTest")
    void testUpdateDateNegativeUpdateBalance() {
        AccountEntity accountEntitySaved = accountService.createRecord(accountEntity);
        accountEntitySaved.setBalanceDate(LocalDate.now().minusDays(5));

        ServiceException thrown = assertThrows(
                ServiceException.class,
                () -> accountService.updateRecord(accountEntitySaved));
    }

}
