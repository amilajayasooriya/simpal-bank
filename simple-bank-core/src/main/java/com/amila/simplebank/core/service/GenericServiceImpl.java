package com.amila.simplebank.core.service;

import com.amila.simplebank.base.entity.BaseEntity;
import com.amila.simplebank.core.exception.ServiceException;
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
    public T updateRecord(T model) {
        preValidateUpdate(model);
        return update(model);
    }

    @Override
    public T createRecord(T model) {
        preValidateCreate(model);
        return create(model);
    }

    @Override
    public void deleteRecord(UUID id) {
        delete(id);
    }

    protected void preValidateUpdate(T model) {
        if(model.getId() == null){
            throw new NullPointerException("Model id is null. Please save model first");
        }
    }

    protected void preValidateCreate(T model) {
        if(model.getId() != null){
            throw new ServiceException("Please use update operation for existing models");
        }
    }

    protected T update(T model) {
        return genericRepository.save(model);
    }

    protected T create(T model) {
        return genericRepository.save(model);
    }

    protected void delete(UUID id) {
        genericRepository.deleteById(id);
    }
}
