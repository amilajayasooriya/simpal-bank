package com.amila.simplebank.core.controller;

import com.amila.simplebank.base.dto.BaseEntity;
import com.amila.simplebank.core.service.GenericService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@Slf4j
public class GenericController<T extends BaseEntity> {
    private GenericService<T> genericService = null;

    public GenericController(GenericService<T> genericService) {
        this.genericService = genericService;
    }

    @GetMapping(value = "/get/{id}")
    public Optional<T> get(@PathVariable("id") UUID id) throws Exception {
        log.info("Received request to get");
        return genericService.findById(id);
    }

    @GetMapping(value = "/get")
    public Page<T> getAll(Pageable pageable) throws Exception {
        log.info("Received request to get ALL");
        return genericService.findAll(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<T> create(@RequestBody T model) {
        log.info("Received request to create");
        try {
            return new ResponseEntity<>(genericService.create(model), HttpStatus.OK);
        } catch (Exception e) {
            log.error("ServiceException occurred during create method for {}", model);
            throw e;
        }
    }
}