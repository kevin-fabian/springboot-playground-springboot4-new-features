package com.fabiankevin.springboot_4._02_Resiliency;

import org.springframework.stereotype.Component;

@Component
public class _02_SampleRepositoryImpl implements _02_SampleRepository {
    @Override
    public boolean test() {
        return false;
    }
}
