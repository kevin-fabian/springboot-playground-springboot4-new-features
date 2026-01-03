package com.fabiankevin.springboot_4._01_API_Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//reference https://spring.io/blog/2025/09/16/api-versioning-in-spring

@RestController
@RequestMapping(value = "/api/hello")
public class _01_API_Versioning {

    @GetMapping(version = "1")
    public String helloV1() {
        return "Hello World V1";
    }

    @GetMapping(version = "1.1+")
    public String helloV11() {
        return "Hello World V1.1+";
    }

    @GetMapping( version = "2")
    public String helloV2() {
        return "Hello World V2 with new features";
    }

//    To perform client requests with API versioning, you can use the RestClient as follows:
//    RestClient client = RestClient.builder()
//            .baseUrl("http://localhost:8080")
//            .apiVersionInserter(ApiVersionInserter.useHeader("API-Version"))
//            .build();
//    or
//    spring.http.client.restclient.apiversion.insert.header=API-Version
//Account account = client.get().uri("/accounts/1")
//        .apiVersion(1.1)
//        .retrieve()
//        .body(Account.class);

}
