package com.henry.image_detector.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyResourceConfig extends ResourceConfig {
	public JerseyResourceConfig() {
		packages("com.henry.image_detector.controller");
	}
}
