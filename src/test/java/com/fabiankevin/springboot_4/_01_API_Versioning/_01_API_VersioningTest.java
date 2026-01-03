package com.fabiankevin.springboot_4._01_API_Versioning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureWebMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class _01_API_VersioningTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_givenHeaderVersion_thenShouldCallV1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
                .header("X-API-Version", "v1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect("Hello World V1"::equals);
    }

    @Test
    void get_givenQueryParameterVersion_thenShouldCallV2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/hello")
                        .queryParam("version", "v2"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect("Hello World V2 with new features"::equals);
    }
}