package com.amila.simplebank.core.service;

import com.amila.simplebank.base.dto.BaseEntity;
import com.amila.simplebank.core.repository.GenericRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T>{
    private final GenericRepository<T> genericRepository;

    public GenericServiceImpl(GenericRepository<T> genericRepository) {
        this.genericRepository = genericRepository;
    }

    @Override
    public Page<T> findAll(Pageable page) {
        return genericRepository.findAll(page);
    }

    @Override
    public Optional<T> findById(UUID id) {
        return genericRepository.findById(id);
    }

    @Override
    public T update(T model) {
        return genericRepository.save(model);
    }

    @Override
    public T create(T model) {
        return genericRepository.save(model);
    }

    @Override
    public void delete(UUID id) {
        genericRepository.deleteById(id);
    }
}
