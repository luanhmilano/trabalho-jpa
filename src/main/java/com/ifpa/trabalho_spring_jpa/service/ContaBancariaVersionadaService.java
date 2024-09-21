package com.ifpa.trabalho_spring_jpa.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifpa.trabalho_spring_jpa.model.ContaBancariaVersionada;
import com.ifpa.trabalho_spring_jpa.repository.ContaBancariaVersionadaRepository;

@Service
public class ContaBancariaVersionadaService {
    
    @Autowired
    private ContaBancariaVersionadaRepository repository;

    public List<ContaBancariaVersionada> findAll() {
        return repository.findAll();
    }

    public Optional<ContaBancariaVersionada> findById(Long id) {
        return repository.findById(id);
    }

    public ContaBancariaVersionada save(ContaBancariaVersionada contaBancaria) {
        return repository.save(contaBancaria);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public synchronized void depositar(Long id, float valor) {
        Optional<ContaBancariaVersionada> conta = repository.findById(id);
        if (conta.isPresent()) {
            ContaBancariaVersionada contaBancaria = conta.get();
            contaBancaria.setSaldo(contaBancaria.getSaldo() + valor);
            contaBancaria.setDataMovimento(LocalDateTime.now());
            repository.save(contaBancaria);
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }

    public synchronized void retirar(Long id, float valor) {
        Optional<ContaBancariaVersionada> conta = repository.findById(id);
        if (conta.isPresent()) {
            ContaBancariaVersionada contaBancaria = conta.get();
            if (contaBancaria.getSaldo() >= valor) {
                contaBancaria.setSaldo(contaBancaria.getSaldo() - valor);
                contaBancaria.setDataMovimento(LocalDateTime.now());
                repository.save(contaBancaria);
            } else {
                throw new RuntimeException("Saldo insuficiente");
            }
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }
}
