package com.henry.image_detector.filter;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;

public class ClientRequestLoggingFilter implements ClientRequestFilter {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void filter(final ClientRequestContext requestContext) {
		LOG.info("{} {}", requestContext.getMethod(), requestContext.getUri());
	}
}
