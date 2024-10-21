package com.henry.image_detector.exception;

public class ImageBase64ConversionException extends RuntimeException {

	public ImageBase64ConversionException(final String message) {
		super(message);
	}

	public ImageBase64ConversionException(final String message, final Throwable cause) {
		super(message, cause);
	}

}