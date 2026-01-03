package com.fabiankevin.springboot_4._04_HttpServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerForHttpClient implements CommandLineRunner {
    @Autowired
    private HelloClient helloClient;
    @Override
    public void run(String... args) throws Exception {
        String greet = helloClient.greet();
        System.out.println("Greet from HelloClient: " + greet);
    }
}
