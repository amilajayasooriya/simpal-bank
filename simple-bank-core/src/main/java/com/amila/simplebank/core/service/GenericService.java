package com.amila.simplebank.core.service;

import com.amila.simplebank.base.dto.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface GenericService<T extends BaseEntity>{
    Page<T> findAll(Pageable page);
    Optional<T> findById(UUID id);
    T update(T model);
    T create(T model);
    void delete(UUID id);
}
