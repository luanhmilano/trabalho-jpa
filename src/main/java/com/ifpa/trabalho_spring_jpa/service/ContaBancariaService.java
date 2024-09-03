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
}
