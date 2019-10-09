package com.abank.backend.controller;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

import com.abank.backend.model.Cliente;
import com.abank.backend.repository.ClienteRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @PostMapping("")
    public ResponseEntity<Page<Cliente>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestBody(required = false) Cliente entity) {
        if(entity == null){
            entity = new Cliente();
        }
        return ResponseEntity.ok(repository.findAll(Example.of(entity), PageRequest.of(page, size)));
    }

    @PostMapping("/")
    public ResponseEntity<Cliente> save(@RequestBody Cliente entity) {
        entity.getEmprestimos().forEach(d->d.setCliente(entity));
        return ResponseEntity.ok(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@RequestBody Cliente entity, @PathVariable Long id) {
        return repository.findById(id).map(cliente -> {
            BeanUtils.copyProperties(entity, cliente, getNullPropertyNames(entity));
            entity.getEmprestimos().forEach(d->d.setCliente(cliente));
            return ResponseEntity.ok(repository.save(cliente));
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
            .map(FeatureDescriptor::getName)
            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
            .toArray(String[]::new);
    }

}
