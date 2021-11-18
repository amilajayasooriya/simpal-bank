package com.amila.simplebank.core.service;

import com.amila.simplebank.base.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface GenericService<T extends BaseEntity>{
    Page<T> findAll(Pageable page);
    Optional<T> findById(UUID id);
    T updateRecord(T model);
    T createRecord(T model);
    void deleteRecord(UUID id);
}
