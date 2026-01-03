package com.fabiankevin.springboot_4._01_API_Versioning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Reference: https://docs.spring.io/spring-framework/reference/testing/resttestclient.html
@SpringBootTest
class _01_API_VersioningTest_RestTestClient {
    RestTestClient client;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        client = RestTestClient.bindToApplicationContext(context).build();
    }

    @Test
    void get_givenHeaderVersion_thenShouldCallV1() {
        client.get()
                .uri("/api/hello")
                .header("X-API-Version", "v1")
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                .expectBody(String.class)
                .isEqualTo("Hello World V1");
    }

    @Test
    void get_givenQueryParameterVersion_thenShouldCallV2() {
        client.get()
                .uri(uriBuilder -> uriBuilder.path("/api/hello")
                        .queryParam("version", "v2")
                        .build())
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader()
                .contentTypeCompatibleWith(MediaType.TEXT_PLAIN)
                .expectBody(String.class)
                .consumeWith(message -> assertThat(message.getResponseBody()).containsIgnoringCase("Hello World V2 with new features"));
    }
}