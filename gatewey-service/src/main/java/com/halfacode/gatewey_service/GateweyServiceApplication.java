package com.halfacode.gatewey_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;

@SpringBootApplication
public class GateweyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateweyServiceApplication.class, args);
	}

	@Bean
	DiscoveryClientRouteDefinitionLocator locator(
			ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}
}
