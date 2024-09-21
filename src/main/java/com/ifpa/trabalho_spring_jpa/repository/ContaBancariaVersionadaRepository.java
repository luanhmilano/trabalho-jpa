package com.ifpa.trabalho_spring_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ifpa.trabalho_spring_jpa.model.ContaBancariaVersionada;

public interface ContaBancariaVersionadaRepository extends JpaRepository<ContaBancariaVersionada, Long> {}