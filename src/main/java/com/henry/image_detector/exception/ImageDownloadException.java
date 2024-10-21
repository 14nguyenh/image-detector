package com.henry.image_detector.exception;

public class ImageDownloadException extends RuntimeException {

	public ImageDownloadException(final String message) {
		super(message);
	}

	public ImageDownloadException(final String message, final Throwable cause) {
		super(message, cause);
	}

}