package com.fabiankevin.springboot_4._02_Resiliency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.retry.RetryException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class _02_Resiliency_ProgramaticalTest {
    @MockitoBean
    private _02_SampleRepository repository;

    @Autowired
    private _02_Resiliency_Programatical resiliency;

    @Test
    void givenRepositoryThrowsException_shouldRetryAndSucceed() throws RetryException {
        when(repository.test())
                .thenThrow(new RuntimeException("Simulated failure"))
                .thenThrow(new RuntimeException("Simulated failure"))
                .thenReturn(true);

        resiliency.save();

        verify(repository, times(3)).test();
    }
}