package com.amila.simplebank.core.service;

import com.amila.simplebank.base.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface GenericService<T extends BaseEntity> {
    /**
     * Get all results with pages
     *
     * @param page parameter
     * @return Page object with data
     */
    Page<T> findAll(Pageable page);

    /**
     * Get one record by the id of it
     *
     * @param id of the record
     * @return record as Options
     */
    Optional<T> findById(UUID id);

    /**
     * Update record method use to update model and it execute pre validation before update
     *
     * @param model to update
     * @return updated model
     */
    T updateRecord(T model);

    /**
     * Create new record. It execute prevalidate method before save
     *
     * @param model to save
     * @return saved model
     */
    T createRecord(T model);

    /**
     * Delete record
     *
     * @param id of the record
     */
    void deleteRecord(UUID id);
}
