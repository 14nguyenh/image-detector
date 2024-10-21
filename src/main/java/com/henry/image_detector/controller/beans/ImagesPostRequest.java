package com.henry.image_detector.controller.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Pattern;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagesPostRequest {
	@Pattern(regexp = "jpg|jpeg|png", message = "Invalid image file type")
	private String dataType;
	private String data;
	private String fileName;

	@Pattern(regexp = "^(https?://.*\\.(jpg|jpeg|png))$", message = "Invalid image URL")
	private String url;

	private boolean objectDetection;

	public String getDataType() {
		return dataType;
	}

	public void setDataType(final String dataType) {
		this.dataType = dataType;
	}

	public String getData() {
		return data;
	}

	public void setData(final String data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public boolean isObjectDetection() {
		return objectDetection;
	}

	public void setObjectDetection(boolean objectDetection) {
		this.objectDetection = objectDetection;
	}
}
