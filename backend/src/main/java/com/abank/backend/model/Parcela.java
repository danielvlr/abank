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

import com.abank.backend.enumeration.TipoSituacao;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parcela")
@Setter @Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    TipoSituacao tipoSituacao;
    LocalDate dataCadastro;
    LocalDate dataVencimento;
    LocalDate dataPagamento;
    BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_emprestimo")
    Emprestimo emprestimo;
}
