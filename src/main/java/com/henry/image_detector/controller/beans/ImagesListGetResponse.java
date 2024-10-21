package com.henry.image_detector.controller.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.henry.image_detector.dto.ImageDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagesListGetResponse {
	private ImageDto image;

	public ImageDto getImage() {
		return image;
	}

	public void setImage(final ImageDto image) {
		this.image = image;
	}
}
