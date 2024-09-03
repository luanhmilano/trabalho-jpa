package com.ifpa.trabalho_spring_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ifpa.trabalho_spring_jpa.model.ContaBancaria;
import com.ifpa.trabalho_spring_jpa.service.ContaBancariaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaBancariaController {
    
    @Autowired
    private ContaBancariaService service;

    @GetMapping
    public List<ContaBancaria> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> findById(@PathVariable Long id) {
        Optional<ContaBancaria> conta = service.findById(id);
        if (conta.isPresent()) {
            return ResponseEntity.ok(conta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> create(@RequestBody ContaBancaria contaBancaria) {
        service.save(contaBancaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaBancaria);
    }

    // Usa a mesma função de save da classe service, apenas mantendo o ID.
    @PutMapping("/{id}")
    public ResponseEntity<ContaBancaria> update(@PathVariable Long id, @RequestBody ContaBancaria contaBancaria) {
        if (service.findById(id).isPresent()) {
            contaBancaria.setId(id);
            return ResponseEntity.ok(service.save(contaBancaria));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
