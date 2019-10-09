package com.abank.backend.controller;

import java.util.List;

import com.abank.backend.model.Cliente;
import com.abank.backend.model.Emprestimo;
import com.abank.backend.repository.ClienteRepository;
import com.abank.backend.repository.EmprestimoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    EmprestimoRepository emprestimoRepository;



    @GetMapping("/find")
    public List<Cliente> find() {
        return clienteRepository.findAll();
    }

    @GetMapping("/find2")
    public List<Emprestimo> find2() {
        return emprestimoRepository.findAll();
    }

}
