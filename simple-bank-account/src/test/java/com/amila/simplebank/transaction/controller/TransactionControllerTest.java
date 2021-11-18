package com.amila.simplebank.transaction.controller;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.account.dto.AccountType;
import com.amila.simplebank.transaction.dto.TransactionDTO;
import com.amila.simplebank.transaction.dto.TransactionType;
import com.amila.simplebank.transaction.entity.TransactionEntity;
import com.amila.simplebank.transaction.service.TransactionService;
import com.amila.simplebank.user.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class TransactionControllerTest {

    private String url;
    private String userUrl;
    private String accountUrl;
    private TestRestTemplate restTemplate;

    private TransactionService transactionService;
    private TransactionDTO transactionDTO;
    private AccountDTO accountDTO;

    @LocalServerPort
    public void setUrl(int port) {
        this.url = String.format("http://localhost:%1$d/simple-bank/transaction/", port);
        this.userUrl = String.format("http://localhost:%1$d/simple-bank/user/", port);
        this.accountUrl = String.format("http://localhost:%1$d/simple-bank/account/", port);
    }

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @BeforeEach
    void beforeEach() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Martin Anderson");
        userDTO.setEmail("martin.anderson@gmail.com");
        userDTO.setNin("67554373D12");

        ResponseEntity<UserDTO> userDTOResponse = restTemplate.postForEntity(userUrl, userDTO, UserDTO.class);

        accountDTO = new AccountDTO();
        accountDTO.setAccountName("Martin Anderson");
        accountDTO.setAccountNumber("109343842234");
        accountDTO.setAccountType(AccountType.SAVINGS);
        accountDTO.setBalance(BigDecimal.valueOf(550.50));
        accountDTO.setBalanceDate(LocalDate.now());
        accountDTO.setCurrency("AUD");
        accountDTO.setTransactionList(Collections.emptyList());
        accountDTO.setUser(userDTOResponse.getBody());

        ResponseEntity<AccountDTO> accountDTOResponse = restTemplate.postForEntity(accountUrl, accountDTO, AccountDTO.class);
        accountDTO = accountDTOResponse.getBody();

        transactionDTO = new TransactionDTO();
        transactionDTO.setTransactionType(TransactionType.Credit);
        transactionDTO.setCreditAmount(BigDecimal.valueOf(100));
        transactionDTO.setValueDate(LocalDate.now());
        transactionDTO.setAccount(accountDTO);
    }

    @Test
    @DisplayName("Create Record")
    void create() {
        ResponseEntity<TransactionDTO> transactionDTOResponse = restTemplate.postForEntity(url, transactionDTO, TransactionDTO.class);
        assertEquals(HttpStatus.OK, transactionDTOResponse.getStatusCode());
    }

    @Test
    @DisplayName("Update Record Negative Test")
    void update() {
        BigDecimal newAmount = BigDecimal.valueOf(2000);

        ResponseEntity<TransactionDTO> transactionDTOResponse = restTemplate.postForEntity(url, transactionDTO, TransactionDTO.class);

        TransactionDTO transactionDTOSaved = transactionDTOResponse.getBody();
        transactionDTOSaved.setDebitAmount(newAmount);
        transactionDTOSaved.setAccount(transactionDTOSaved.getAccount());

        restTemplate.put(url, transactionDTOSaved);

        Optional<TransactionEntity> fromDbOptional = transactionService.findById(transactionDTOSaved.getId());

        //should not be allowed to update transaction
        assertNotEquals(fromDbOptional.get().getCreditAmount(), newAmount);
    }

    @Test
    @DisplayName("Test transactions sum and Account balance")
    void testTransactionSumAndAccountBalance() {
        restTemplate.postForEntity(url, transactionDTO, TransactionDTO.class);

        BigDecimal newValue = BigDecimal.valueOf(2000);

        TransactionDTO secondTransaction = new TransactionDTO();
        secondTransaction.setTransactionType(TransactionType.Credit);
        secondTransaction.setValueDate(LocalDate.now());
        secondTransaction.setCreditAmount(newValue);
        secondTransaction.setAccount(accountDTO);
        restTemplate.postForEntity(url, secondTransaction, TransactionDTO.class);

        ResponseEntity<AccountDTO> accountDTOResponseEntity = restTemplate.getForEntity(accountUrl + "/" + accountDTO.getId().toString(), AccountDTO.class);
        AccountDTO accountEntityFromDB = accountDTOResponseEntity.getBody();

        BigDecimal transActionSum = accountEntityFromDB.getTransactionList().stream().map(TransactionDTO::getCreditAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal accountBalance = accountEntityFromDB.getBalance();

        assertEquals(transActionSum, accountBalance);
    }

    @Test
    @DisplayName("Test transactions Date and Balanced Date")
    void testTransactionDateAndAccountDate() {
        restTemplate.postForEntity(url, transactionDTO, TransactionDTO.class);

        BigDecimal newValue = BigDecimal.valueOf(2000);

        TransactionDTO secondTransaction = new TransactionDTO();
        secondTransaction.setTransactionType(TransactionType.Credit);
        secondTransaction.setValueDate(LocalDate.now());
        secondTransaction.setCreditAmount(newValue);
        secondTransaction.setAccount(accountDTO);
        restTemplate.postForEntity(url, secondTransaction, TransactionDTO.class);

        ResponseEntity<AccountDTO> accountDTOResponseEntity = restTemplate.getForEntity(accountUrl + "/" + accountDTO.getId().toString(), AccountDTO.class);
        AccountDTO accountEntityFromDB = accountDTOResponseEntity.getBody();

        LocalDate latestDate = accountEntityFromDB.getTransactionList().stream().map(TransactionDTO::getValueDate)
                .max(LocalDate::compareTo).get();
        LocalDate accountDate = accountEntityFromDB.getBalanceDate();

        assertEquals(latestDate, accountDate);
    }
}
