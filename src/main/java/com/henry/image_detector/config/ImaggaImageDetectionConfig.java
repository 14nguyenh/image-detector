package com.henry.image_detector.config;

import java.util.Base64;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.inject.Inject;

@Component
public class ImaggaImageDetectionConfig implements ImageDetectionConfig {
	private static final String API_KEY = "imagga.apiKey";
	private static final String API_SECRET = "imagga.apiSecret";
	private static final String API_ENDPOINT = "imagga.endpoint";

	private final String authKey;
	private final String apiEndpoint;

	@Inject
	public ImaggaImageDetectionConfig(final Environment environment) {
		String apiKey = environment.getProperty(API_KEY, "");
		String apiSecret = environment.getProperty(API_SECRET, "");
		String credentials = apiKey + ":" + apiSecret;
		authKey = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
		apiEndpoint = environment.getProperty(API_ENDPOINT, "");
	}

	@Override
	public String getBasicAuthKey() {
		return authKey;
	}

	@Override
	public String getApiEndpoint() {
		return apiEndpoint;
	}
}
