package com.abank.backend.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter @Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String agencia;
    String conta;
    LocalDate dataCadastro;
    
    @OneToMany(mappedBy="cliente")
    Set<Emprestimo> emprestimos;

    @Transient Integer quaInteger; 
    @Transient BigDecimal valorTotalSolicitado;
    @Transient BigDecimal valorTotalPago;
    @Transient BigDecimal valorTotalPeloPago;
}
