package com.amila.simplebank.user.controller;

import com.amila.simplebank.user.dto.UserDTO;
import com.amila.simplebank.user.entity.UserEntity;
import com.amila.simplebank.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    private static UserDTO userDTO;

    private String url;
    private TestRestTemplate restTemplate;
    private UserService userService;

    @LocalServerPort
    public void setUrl(int port) {
        this.url = String.format("http://localhost:%1$d/simple-bank/user/", port);
    }

    @BeforeEach
    void beforeEach() {
        userDTO = new UserDTO();
        userDTO.setName("Martin");
        userDTO.setEmail("martin.jhone@gmail.com");
        userDTO.setNin("7894373D12");
        userDTO.setAccountEntityList(Collections.emptyList());
    }

    @Autowired
    public void setRestTemplate(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @AfterEach
    void deleteRecord() {
        userService.deleteRecord(userDTO.getId());
    }

    @Test
    @DisplayName("Create Record")
    void create() {
        ResponseEntity<UserDTO> userDTOResponse = restTemplate.postForEntity(url, userDTO, UserDTO.class);
        userDTO.setId(userDTOResponse.getBody().getId());
        assertEquals(HttpStatus.OK, userDTOResponse.getStatusCode());
    }

    @Test
    @DisplayName("Update Record")
    void update() {
        String newName = "Thomas";

        ResponseEntity<UserDTO> userDTOResponse = restTemplate.postForEntity(url, userDTO, UserDTO.class);
        userDTO.setId(userDTOResponse.getBody().getId());

        UserDTO userDTOSaved = userDTOResponse.getBody();
        userDTOSaved.setName(newName);

        restTemplate.put(url, userDTOSaved);

        Optional<UserEntity> fromDbOptional = userService.findById(userDTOSaved.getId());
        assertEquals(fromDbOptional.get().getName(), newName);
    }
}
