package com.fabiankevin.springboot_4._03_ConfigurationProperties_JavaRecord;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record AppProperties(String name, String version) {}