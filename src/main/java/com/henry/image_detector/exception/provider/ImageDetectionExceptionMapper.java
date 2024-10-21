package com.henry.image_detector.exception.provider;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.henry.image_detector.exception.ImageDetectionException;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ImageDetectionExceptionMapper implements ExceptionMapper<ImageDetectionException> {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public Response toResponse(final ImageDetectionException e) {
		LOG.warn("Mapping {} to response", e.getClass().getSimpleName(), e);

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(e.getMessage())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
