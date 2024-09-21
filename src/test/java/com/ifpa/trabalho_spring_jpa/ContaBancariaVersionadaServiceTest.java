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

import com.ifpa.trabalho_spring_jpa.model.ContaBancariaVersionada;
import com.ifpa.trabalho_spring_jpa.repository.ContaBancariaVersionadaRepository;
import com.ifpa.trabalho_spring_jpa.service.ContaBancariaVersionadaService;

public class ContaBancariaVersionadaServiceTest {

    @Mock
    private ContaBancariaVersionadaRepository repository;

    @InjectMocks
    private ContaBancariaVersionadaService service;

    private ContaBancariaVersionada conta;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        conta = new ContaBancariaVersionada();
        conta.setId(1L);
        conta.setNomeCliente("Cliente Teste");
        conta.setSaldo(1000);
    }

    @Test
    public void testarOperacoesConcorrentes() throws InterruptedException {
        when(repository.findById(1L)).thenReturn(Optional.of(conta));
        when(repository.save(any(ContaBancariaVersionada.class))).thenReturn(conta);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> service.depositar(1L, 200));  // Depósito de 200
            executor.submit(() -> service.retirar(1L, 100));    // Retirada de 100
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        verify(repository, times(10)).save(any(ContaBancariaVersionada.class));
    }
}
