package com.fabiankevin.springboot_4._04_HttpServiceClient;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

public interface HelloClient {
    @GetExchange("/api/hello")
    String greet();
}
