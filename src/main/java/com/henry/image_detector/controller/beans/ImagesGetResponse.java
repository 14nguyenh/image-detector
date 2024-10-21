package com.henry.image_detector.controller.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.henry.image_detector.dto.ImageDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagesGetResponse {
	private List<ImageDto> images;

	public ImagesGetResponse(List<ImageDto> images) {
		this.images = images;
	}

	public List<ImageDto> getImages() {
		return images;
	}

	public void setImages(final List<ImageDto> images) {
		this.images = images;
	}
}
