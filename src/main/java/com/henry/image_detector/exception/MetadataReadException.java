package com.henry.image_detector.exception;

public class MetadataReadException extends RuntimeException {

	public MetadataReadException(final String message) {
		super(message);
	}

	public MetadataReadException(final String message, final Throwable cause) {
		super(message, cause);
	}

}