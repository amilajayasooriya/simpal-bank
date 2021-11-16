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
    public Optional<T> get(@PathVariable("id") UUID id) {
        log.debug("Received get request for id {}", id);
        return genericService.findById(id);
    }

    @GetMapping(value = "/get")
    public Page<T> getAll(Pageable pageable) {
        log.debug("Received get ALL request");
        return genericService.findAll(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<T> create(@RequestBody T model) {
        log.debug("Received request to create model {}", model);
        return new ResponseEntity<>(genericService.createRecord(model), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<T> update(@RequestBody T model) {
        log.debug("Received request to update model {}", model);
        return new ResponseEntity<>(genericService.updateRecord(model), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        log.debug("Received request to delete model with id {}", id);
        genericService.deleteRecord(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
