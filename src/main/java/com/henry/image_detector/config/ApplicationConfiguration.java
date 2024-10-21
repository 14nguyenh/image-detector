package com.henry.image_detector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.henry.image_detector.filter.ClientRequestLoggingFilter;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

@Configuration
public class ApplicationConfiguration {
	@Bean
	public Client getClient() {
		return ClientBuilder.newBuilder()
				.register(ClientRequestLoggingFilter.class)
				.build();
	}
}
