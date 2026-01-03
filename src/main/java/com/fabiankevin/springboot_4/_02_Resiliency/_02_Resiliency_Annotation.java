package com.fabiankevin.springboot_4._02_Resiliency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.EnableResilientMethods;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableResilientMethods
public class _02_Resiliency_Annotation {
    private final _02_SampleRepository repository;

    @Retryable(maxRetries = 3,
            jitter = 10,
            delay = 100, multiplier = 2, maxDelay = 1000)
    @ConcurrencyLimit(3)
    public void send(){
        log.info("Resiliency test called.");
        repository.test();
    }
}
