package com.amila.simplebank.account.controller;

import com.amila.simplebank.account.dto.AccountDTO;
import com.amila.simplebank.account.dto.AccountType;
import com.amila.simplebank.account.entity.AccountEntity;
import com.amila.simplebank.account.service.AccountService;
import com.amila.simplebank.user.dto.UserDTO;
import com.amila.simplebank.user.entity.UserEntity;
import com.amila.simplebank.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class AccountControllerTest {
    private static AccountDTO accountDTO;

    private String url;
    private String userUrl;
    private TestRestTemplate restTemplate;

    private AccountService accountService;

    @LocalServerPort
    public void setUrl(int port) {
        this.url = String.format("http://localhost:%1$d/simple-bank/account/", port);
        this.userUrl = String.format("http://localhost:%1$d/simple-bank/user/", port);
    }

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
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
    }

    @Test
    @DisplayName("Create Record")
    void create() {
        ResponseEntity<AccountDTO> accountDTOResponse = restTemplate.postForEntity(url, accountDTO, AccountDTO.class);
        accountDTO.setId(accountDTOResponse.getBody().getId());
        assertEquals(HttpStatus.OK, accountDTOResponse.getStatusCode());
    }

    @Test
    @DisplayName("Update Record")
    void update() {
        String newName = "Thomas";

        ResponseEntity<AccountDTO> accountDTOResponse = restTemplate.postForEntity(url, accountDTO, AccountDTO.class);

        AccountDTO accountDTOSaved = accountDTOResponse.getBody();
        accountDTOSaved.setAccountName(newName);
        accountDTOSaved.setUser(accountDTO.getUser());

        restTemplate.put(url, accountDTOSaved);

        Optional<AccountEntity> fromDbOptional = accountService.findById(accountDTOSaved.getId());
        assertEquals(fromDbOptional.get().getAccountName(), newName);
    }
}
