package com.henry.image_detector.exception;

public class ImageNotFoundException extends RuntimeException {

	public ImageNotFoundException(final String message) {
		super(message);
	}

	public ImageNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

}