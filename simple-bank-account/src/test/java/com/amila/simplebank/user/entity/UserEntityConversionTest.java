package com.amila.simplebank.user.entity;

import com.amila.simplebank.user.dto.UserDTO;
import com.amila.simplebank.user.mapper.UserEntityMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserEntityConversionTest {
    private UserEntityMapper userEntityMapper;

    @Autowired
    public void setUserEntityMapper(UserEntityMapper userEntityMapper) {
        this.userEntityMapper = userEntityMapper;
    }

    @Test
    @DisplayName("User Entity to DTO test")
    void dtoToEntityTest(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("Martin");
        userEntity.setEmail("martin.jhone@gmail.com");
        userEntity.setNin("7894373D12");
        userEntity.setAccountEntityList(Collections.emptyList());

        UserDTO userDTO = userEntityMapper.toDto(userEntity);
        assertTrue(isAllPropertiesEqual(userEntity, userDTO));
    }

    @Test
    @DisplayName("User DTO to Entity test")
    void entityToDtoTest(){
        UserDTO userDto = new UserDTO();
        userDto.setName("Martin");
        userDto.setEmail("martin.jhone@gmail.com");
        userDto.setNin("7894373D12");
        userDto.setAccountEntityList(Collections.emptyList());

        UserEntity userEntity = userEntityMapper.toEntity(userDto);
        assertTrue(isAllPropertiesEqual(userEntity, userDto));
    }

    private boolean isAllPropertiesEqual(UserEntity a, UserDTO b) {
        return a.getName().equals(b.getName()) &&
                a.getEmail().equals(b.getEmail()) &&
                a.getNin().equals(b.getNin());
    }
}
