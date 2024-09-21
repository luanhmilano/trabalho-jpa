package com.ifpa.trabalho_spring_jpa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ifpa.trabalho_spring_jpa.model.ContaBancariaVersionada;
import com.ifpa.trabalho_spring_jpa.service.ContaBancariaVersionadaService;

@RestController
@RequestMapping("/contas-versionadas")
public class ContaBancariaVersionadaController {
    @Autowired
    private ContaBancariaVersionadaService service;

    @GetMapping
    public List<ContaBancariaVersionada> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancariaVersionada> findById(@PathVariable Long id) {
        Optional<ContaBancariaVersionada> conta = service.findById(id);
        if (conta.isPresent()) {
            return ResponseEntity.ok(conta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ContaBancariaVersionada> create(@RequestBody ContaBancariaVersionada contaBancaria) {
        service.save(contaBancaria);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaBancaria);
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<Void> depositar(@PathVariable Long id, @RequestParam float valor) {
        try {
            service.depositar(id, valor);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/{id}/retirar")
    public ResponseEntity<Void> retirar(@PathVariable Long id, @RequestParam float valor) {
        try {
            service.retirar(id, valor);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
