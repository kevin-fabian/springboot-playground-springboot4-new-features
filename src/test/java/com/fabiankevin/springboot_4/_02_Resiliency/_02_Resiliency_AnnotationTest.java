package com.fabiankevin.springboot_4._02_Resiliency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;

@SpringBootTest
class _02_Resiliency_AnnotationTest {
    @MockitoBean
    private _02_SampleRepository repository;

    @Autowired
    private _02_Resiliency_Annotation resiliency;

    @Test
    void givenRepositoryThrowsException_shouldRetryAndSucceed() {
        when(repository.test())
                .thenThrow(new RuntimeException("Simulated failure"))
                .thenThrow(new RuntimeException("Simulated failure"))
                .thenReturn(true);

        resiliency.send();

        verify(repository, times(3)).test();
    }
}
