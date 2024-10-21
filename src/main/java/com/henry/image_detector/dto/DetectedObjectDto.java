package com.henry.image_detector.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetectedObjectDto {
	private String name;
	private double confidence;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(final double confidence) {
		this.confidence = confidence;
	}
}
