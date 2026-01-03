package com.fabiankevin.springboot_4._03_ConfigurationProperties_JavaRecord;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AppPropertiesTest {

    @Autowired
    private AppProperties appProperties;

    @Test
    void testAppProperties() {
        assertEquals("SpringBoot4 Application", appProperties.name());
        assertEquals("0.0.1-SNAPSHOT", appProperties.version());
    }
}
