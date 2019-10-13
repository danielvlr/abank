package com.abank.backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;

import com.abank.backend.enumeration.TipoJuros;
import com.abank.backend.enumeration.TipoSituacao;
import com.abank.backend.model.Emprestimo;
import com.abank.backend.model.Parcela;

import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    public Emprestimo calcularEmprestimo(Emprestimo emprestimo) {
        if (emprestimo.getTipoJuros() == TipoJuros.COMPOSTO) {
            calcularJurosComposto(emprestimo);
        }
        
        if (emprestimo.getTipoJuros() == TipoJuros.SIMPLES) {
            calcularJurosSimples(emprestimo);
        }
        return emprestimo;
    }

    private void calcularJurosComposto(Emprestimo emprestimo) {
        BigDecimal taxa = emprestimo.getTaxa();
        Integer quantidadeParcelas = emprestimo.getQuantidadeParcelas();
        BigDecimal valorSolicitado = emprestimo.getValorSolicitado();
        Double valor = valorSolicitado.doubleValue() * Math.pow((1 + taxa.doubleValue()/100),quantidadeParcelas);
        criarPacelas(emprestimo, new BigDecimal(valor/quantidadeParcelas));
    }

	private void calcularJurosSimples(Emprestimo emprestimo) {
        BigDecimal taxa = emprestimo.getTaxa();
        Integer quantidadeParcelas = emprestimo.getQuantidadeParcelas();
        BigDecimal valorSolicitado = emprestimo.getValorSolicitado();
        Double valor = valorSolicitado.doubleValue() * Math.pow((1 + taxa.doubleValue()/100),quantidadeParcelas);
        criarPacelas(emprestimo, new BigDecimal(valor/quantidadeParcelas));
    }

    private void criarPacelas(Emprestimo emprestimo, BigDecimal valor) {
        emprestimo.setParcela(new HashSet<>());
        for (int i = 0; i < emprestimo.getQuantidadeParcelas(); i++) {
            Parcela parcela = new Parcela();
            parcela.setEmprestimo(emprestimo);
            parcela.setDataCadastro(LocalDate.now());
            parcela.setDataVencimento(LocalDate.now().plusMonths(i+1));
            parcela.setValor(valor);
            parcela.setTipoSituacao(TipoSituacao.Cadastrado);
            emprestimo.getParcela().add(parcela);
        }
    }
}