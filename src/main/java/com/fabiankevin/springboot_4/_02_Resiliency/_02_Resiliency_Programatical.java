package com.fabiankevin.springboot_4._02_Resiliency;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class _02_Resiliency_Programatical {
    private final _02_SampleRepository repository;

    public void save() throws RetryException {
        log.info("Preparing to call repository test method.");
        var retryPolicy = RetryPolicy.builder()
                .maxRetries(4)
                .delay(Duration.ofMillis(100))
                .jitter(Duration.ofMillis(10))
                .multiplier(2)
                .maxDelay(Duration.ofSeconds(1))
                .build();

        var retryTemplate = new RetryTemplate(retryPolicy);
        retryTemplate.execute(() -> {
                log.info("Resiliency test called.");
                return repository.test();
            }
        );
    }
}
