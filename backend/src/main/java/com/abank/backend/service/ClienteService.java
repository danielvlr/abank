package com.abank.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import com.abank.backend.model.Cliente;
import com.abank.backend.model.Emprestimo;
import com.abank.backend.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService{

  @Autowired
  ClienteRepository clienteRepository;

  @Bean
  @Transactional
  public void init(){
      Cliente cliente = new Cliente();
      cliente.setNome("Daniel Leitao Vilar");
      clienteRepository.save(cliente);

      Cliente cliente2 = new Cliente();
      cliente2.setNome("Daniel2");
      cliente2.setEmprestimos(new HashSet<Emprestimo>());
      
      Emprestimo emprestimo = new Emprestimo();
      emprestimo.setCliente(cliente2);
      emprestimo.setDataCadastro(LocalDate.now());
      emprestimo.setValorTotal(new BigDecimal("1000"));
      emprestimo.setValorPago(BigDecimal.ZERO);
      emprestimo.setValorApagar(BigDecimal.ZERO);

      cliente2.getEmprestimos().add(emprestimo);
  
      clienteRepository.save(cliente2);



  }
}