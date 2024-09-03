package com.ifpa.trabalho_spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifpa.trabalho_spring_jpa.model.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
}
