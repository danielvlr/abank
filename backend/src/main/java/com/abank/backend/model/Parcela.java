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

import com.abank.backend.enumeration.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parcela")
@Getter @Setter
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Status status;
    LocalDate dataCadastro;
    LocalDate dataVencimento;
    BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_emprestimo", nullable=false)
    Emprestimo emprestimo;

}
