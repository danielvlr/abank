package com.abank.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.abank.backend.enumeration.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Emprestimo")
@Getter @Setter
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Status status;
    BigDecimal valorTotal;
    LocalDate dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cliente_id", nullable=false)
    Cliente cliente;

  
    @Transient BigDecimal valorPago;
    @Transient BigDecimal valorApagar;
}
