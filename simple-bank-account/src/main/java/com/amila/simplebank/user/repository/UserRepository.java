package com.amila.simplebank.user.repository;

import com.amila.simplebank.core.repository.GenericRepository;
import com.amila.simplebank.user.dto.UserEntity;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends GenericRepository<UserEntity> {
}
