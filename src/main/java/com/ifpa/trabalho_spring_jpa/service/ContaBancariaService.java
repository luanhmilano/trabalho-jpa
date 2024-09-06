package com.ifpa.trabalho_spring_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpa.trabalho_spring_jpa.model.ContaBancaria;
import com.ifpa.trabalho_spring_jpa.repository.ContaBancariaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContaBancariaService {
    
    @Autowired
    private ContaBancariaRepository repository;

    public List<ContaBancaria> findAll() {
        return repository.findAll();
    }

    public Optional<ContaBancaria> findById(Long id) {
        return repository.findById(id);
    }

    public ContaBancaria save(ContaBancaria contaBancaria) {
        return repository.save(contaBancaria);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void depositar(Long id, float valor) { 
        Optional<ContaBancaria> conta = repository.findById(id); 
        if (conta.isPresent()) { 
            ContaBancaria contaBancaria = conta.get();
            contaBancaria.setSaldo(contaBancaria.getSaldo() + valor);
            repository.save(contaBancaria);
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }

    public void retirar(Long id, float valor) {
        Optional<ContaBancaria> conta = repository.findById(id);
        if (conta.isPresent()) {
            ContaBancaria contaBancaria = conta.get();
            if (contaBancaria.getSaldo() >= valor) {
                contaBancaria.setSaldo(contaBancaria.getSaldo() - valor);
                repository.save(contaBancaria);
            } else {
                throw new RuntimeException("Saldo insuficiente.");
            }
        } else {
            throw new RuntimeException("Conta não encontrada.");
        }
    }
}
