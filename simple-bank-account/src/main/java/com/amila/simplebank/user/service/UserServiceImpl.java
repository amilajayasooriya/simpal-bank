package com.amila.simplebank.user.service;

import com.amila.simplebank.core.service.GenericServiceImpl;
import com.amila.simplebank.user.entity.UserEntity;
import com.amila.simplebank.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<UserEntity> implements UserService {

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }
}
