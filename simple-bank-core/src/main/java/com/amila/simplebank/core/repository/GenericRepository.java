package com.amila.simplebank.core.repository;

import com.amila.simplebank.base.dto.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, UUID> {
    Optional<T> findById(UUID id);

    @Override
    Page<T> findAll(Pageable pageable);
}
