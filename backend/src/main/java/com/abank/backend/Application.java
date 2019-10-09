package com.abank.backend;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.abank.backend.enumeration.Status;
import com.abank.backend.model.Cliente;
import com.abank.backend.model.Emprestimo;
import com.abank.backend.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


 	@Autowired
	ClienteRepository clienteRepository;

    @Bean
    public void init(){
        Cliente cliente = new Cliente();
        Emprestimo emprestimo = new Emprestimo();
        
        
        cliente.setNome("Daniel Leitao Vilar");
        cliente.setAgencia("12181");
        cliente.setConta("230278");

        Set<Emprestimo> hs = new HashSet<>();


		emprestimo.setCliente(cliente);
        emprestimo.setDataCadastro(LocalDate.now());
        emprestimo.setStatus(Status.Cadastrado);
        emprestimo.setValorTotal(new BigDecimal("10"));
        hs.add(emprestimo);

        // emprestimo.setParcela(new ArrayList<Parcela>());
        // emprestimo.getParcela().add(parcela);


        // parcela.setDataPagamento(LocalDate.now());
        // parcela.setDataVecimento(LocalDate.now());
        // parcela.setStatus(Status.Cadastrado);

        cliente.setEmprestimos(hs);

        clienteRepository.save(cliente);
    }
}
