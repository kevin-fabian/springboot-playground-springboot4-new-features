package com.fabiankevin.springboot_4;

import com.fabiankevin.springboot_4._03_ConfigurationProperties_JavaRecord.AppProperties;
import com.fabiankevin.springboot_4._04_HttpServiceClient.HelloClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class})
@ImportHttpServices(group="greet", types = HelloClient.class)
public class Springboot4Application {

	static void main(String[] args) {
		SpringApplication.run(Springboot4Application.class, args);
	}

}
