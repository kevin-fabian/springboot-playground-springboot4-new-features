package com.fabiankevin.springboot_4._04_HttpServiceClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;

@Configuration(proxyBeanMethods = false)
@Slf4j
public class MyHttpServiceGroupConfiguration {

//	@Bean
//	RestClientHttpServiceGroupConfigurer myHttpServiceGroupConfigurer() {
//		return (groups) -> groups.forEachClient((group, clientBuilder) -> {
//			String groupName = group.name();
//
//			// Example: Add a default header to all requests in this group
//			clientBuilder.defaultHeader("service-group", groupName);
//		});
//	}

}