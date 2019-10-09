package com.abank.backend.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.abank.backend.enumeration.Status;
import com.abank.backend.enumeration.TipoEmprestimo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "emprestimo")
@Setter @Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Status status;
    BigDecimal valorTotal;
    LocalDate dataCadastro;
    BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    TipoEmprestimo tipoEmprestimo; 
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="id_cliente")
    Cliente cliente;

    @JsonBackReference
    @OneToMany(mappedBy="emprestimo",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    Set<Parcela> parcela;

    @Transient BigDecimal valorPago;
    @Transient BigDecimal valorApagar;
}
