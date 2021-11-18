package com.amila.simplebank.user.service;

import com.amila.simplebank.user.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceTest {
    private UserService userService;
    private UserEntity userEntity;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void beforeEach() {
        userEntity = new UserEntity();
        userEntity.setName("Martin");
        userEntity.setEmail("martin.jhone@gmail.com");
        userEntity.setNin("7894373D12");
        userEntity.setAccountEntityList(Collections.emptyList());
    }

    @AfterEach
    void deleteRecord(){
        userService.deleteRecord(userEntity.getId());
    }

    @Test
    @DisplayName("Test user create via service layer")
    void createServiceTest() {
        UserEntity createdUer = userService.createRecord(userEntity);
        assertNotNull(createdUer);
    }

    @Test
    @DisplayName("Test user ID generated via service layer")
    void createServiceIDAutoGenTest() {
        UserEntity createdUer = userService.createRecord(userEntity);
        assertNotNull(createdUer.getId());
    }

    @Test
    @DisplayName("Test user Update via service layer")
    void updateServiceIDAutoGenTest() {
        String newName = "Thomas";
        UserEntity createdUer = userService.createRecord(userEntity);
        createdUer.setName(newName);
        userService.updateRecord(createdUer);

        Optional<UserEntity> fromDbOptional = userService.findById(userEntity.getId());

        assertEquals(fromDbOptional.get().getName(), newName);
    }
}
