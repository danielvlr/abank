package com.abank.backend.repository;

import com.abank.backend.model.Parcela;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long>{

}