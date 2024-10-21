package com.henry.image_detector.exception;

public class ImageDetectionException extends RuntimeException {

	public ImageDetectionException(final String message) {
		super(message);
	}

	public ImageDetectionException(final String message, final Throwable cause) {
		super(message, cause);
	}

}