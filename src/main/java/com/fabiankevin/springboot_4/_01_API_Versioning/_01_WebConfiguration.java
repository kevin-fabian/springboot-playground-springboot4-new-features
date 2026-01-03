package com.fabiankevin.springboot_4._01_API_Versioning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class _01_WebConfiguration implements WebMvcConfigurer {

	@Override
	public void configureApiVersioning(ApiVersionConfigurer configurer) {
//		configurer.usePathSegment(1); // e.g., /v1/api/hell
		// path segment versioning won't work properly with query parameters or headers
        configurer.useQueryParam("version");
        configurer.useRequestHeader("X-API-Version");
        configurer.setDefaultVersion("1.0");
	}
}