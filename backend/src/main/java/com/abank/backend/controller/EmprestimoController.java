package com.abank.backend.controller;

import com.abank.backend.model.Emprestimo;
import com.abank.backend.model.Emprestimo;
import com.abank.backend.repository.EmprestimoRepository;
import com.abank.backend.service.EmprestimoService;

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
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    EmprestimoRepository repository;

    @Autowired
    EmprestimoService service;


    @PostMapping("/calcularEmprestimo")
    public ResponseEntity<Emprestimo> calcularEmprestimo(@RequestBody Emprestimo entity) {
        return ResponseEntity.ok(service.calcularEmprestimo(entity));
    }

    @PostMapping("")
    public ResponseEntity<Page<Emprestimo>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestBody(required = false) Emprestimo entity) {
        if(entity == null){
            entity = new Emprestimo();
        }
        return ResponseEntity.ok(repository.findAll(Example.of(entity), PageRequest.of(page, size)));
    }

    @PostMapping("/")
    public ResponseEntity<Emprestimo> save(@RequestBody Emprestimo entity) {
        return ResponseEntity.ok(repository.save(entity));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> update(@RequestBody Emprestimo entity, @PathVariable Long id) {
        return repository.findById(id).map(emprestimo -> {
            return ResponseEntity.ok(repository.save(emprestimo));
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
