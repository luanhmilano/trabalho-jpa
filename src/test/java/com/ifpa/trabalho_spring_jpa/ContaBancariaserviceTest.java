package com.ifpa.trabalho_spring_jpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ifpa.trabalho_spring_jpa.model.ContaBancaria;
import com.ifpa.trabalho_spring_jpa.repository.ContaBancariaRepository;
import com.ifpa.trabalho_spring_jpa.service.ContaBancariaService;

public class ContaBancariaserviceTest {

    @Mock // explain this
    private ContaBancariaRepository repository;

    @InjectMocks // explain this
    private ContaBancariaService service;

    private ContaBancaria conta;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        conta = new ContaBancaria();
        conta.setId(1L);
        conta.setNomeCliente("Teste");
        conta.setSaldo(1000);
    }

    @Test
    public void testarOperacoesConcorrentes() throws InterruptedException {
        when(repository.findById(1L)).thenReturn(Optional.of(conta));
        when(repository.save(any(ContaBancaria.class))).thenReturn(conta);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> service.depositar(1L, 200));
            executor.submit(() -> service.retirar(1L, 100));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        verify(repository, times(10)).save(any(ContaBancaria.class));
    }
}
