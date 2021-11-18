package com.amila.simplebank.core.controller;

import com.amila.simplebank.base.dto.BaseDTO;
import com.amila.simplebank.base.entity.BaseEntity;
import com.amila.simplebank.base.mapper.GenericEntityMapper;
import com.amila.simplebank.core.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@Slf4j
public class GenericController<D extends BaseDTO, E extends BaseEntity> {
    private GenericService<E> genericService = null;
    private GenericEntityMapper<D, E> genericEntityMapper = null;

    public GenericController(GenericService<E> genericService, GenericEntityMapper genericEntityMapper) {
        this.genericService = genericService;
        this.genericEntityMapper = genericEntityMapper;
    }

    @GetMapping(value = "/{id}")
    public Optional<D> get(@PathVariable("id") UUID id) {
        log.debug("Received get request for id {}", id);
        return genericService.findById(id).map(e -> genericEntityMapper.toDto(e));
    }

    @GetMapping
    public Page<D> getAll(Pageable pageable) {
        log.debug("Received get ALL request");
        Page<E> entityPage = genericService.findAll(pageable);
        return new PageImpl<>(genericEntityMapper.toDtoList(entityPage.getContent()), entityPage.getPageable(),
                entityPage.getTotalElements());
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D model) {
        log.debug("Received request to create model {}", model);
        return new ResponseEntity<>(genericEntityMapper.toDto(genericService.createRecord(genericEntityMapper.toEntity(model))), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<D> update(@RequestBody D model) {
        log.debug("Received request to update model {}", model);
        return new ResponseEntity<>(genericEntityMapper.toDto(genericService.updateRecord(genericEntityMapper.toEntity(model))),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        log.debug("Received request to delete model with id {}", id);
        genericService.deleteRecord(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
