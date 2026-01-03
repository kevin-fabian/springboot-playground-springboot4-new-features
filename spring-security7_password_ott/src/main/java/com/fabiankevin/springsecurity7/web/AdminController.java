package com.fabiankevin.springsecurity7.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequestMapping("/v1/api/admin")
public class AdminController {

    @GetMapping
    public String home(){
        return "Hello, Admin!";
    }

}
