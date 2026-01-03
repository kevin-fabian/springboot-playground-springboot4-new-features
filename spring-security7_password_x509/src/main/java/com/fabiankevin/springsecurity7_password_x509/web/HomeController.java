package com.fabiankevin.springsecurity7_password_x509.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // X509 authentication can access this endpoint
    @GetMapping("/")
    public String home() {
        return "Welcome to the Home Page!";
    }

    // X509 and Password are needed to access this endpoint
    @GetMapping("/secure")
    public String secure() {
        return "Welcome to the Secure Page!";
    }
}