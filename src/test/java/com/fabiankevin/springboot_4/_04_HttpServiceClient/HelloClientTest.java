package com.fabiankevin.springboot_4._04_HttpServiceClient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
properties = {
        "server.port=9999",
        "spring.http.serviceclient.greet.base-url=http://localhost:9999"
    })
class HelloClientTest {

    @Autowired
    private HelloClient helloClient;

    @Test
    void testServiceClient() {
       String message = helloClient.greet();
       assertEquals("Hello, World!", message);
    }
}