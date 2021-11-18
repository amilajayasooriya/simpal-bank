package com.amila.simplebank.user.controller;

import com.amila.simplebank.core.controller.GenericController;
import com.amila.simplebank.user.dto.UserDTO;
import com.amila.simplebank.user.entity.UserEntity;
import com.amila.simplebank.user.mapper.UserEntityMapper;
import com.amila.simplebank.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/${spring.application.name}/user")
@RestController
public class UserController extends GenericController<UserDTO, UserEntity> {

    public UserController(UserService userService, UserEntityMapper userEntityMapper) {
        super(userService, userEntityMapper);
    }
}
