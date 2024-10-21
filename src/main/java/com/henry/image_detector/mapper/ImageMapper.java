package com.henry.image_detector.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.henry.image_detector.controller.beans.ImagesPostRequest;
import com.henry.image_detector.controller.beans.ImagesPostResponse;
import com.henry.image_detector.dto.ImageDto;
import com.henry.image_detector.repository.entity.ImageEntity;

@Mapper(componentModel = "spring")
public interface ImageMapper {
	@Mapping(target = "uuid", ignore = true)
	@Mapping(target = "name", ignore = true)
	@Mapping(target = "width", ignore = true)
	@Mapping(target = "height", ignore = true)
	@Mapping(target = "type", ignore = true)
	@Mapping(target = "size", ignore = true)
	@Mapping(target = "objects", ignore = true)
	ImageDto toDto(ImagesPostRequest imagesPostRequest);

	ImagesPostResponse toImagesPostResponse(ImageDto imageDto);

	@Mapping(target = "dataType", ignore = true)
	@Mapping(target = "data", ignore = true)
	@Mapping(target = "fileName", ignore = true)
	@Mapping(target = "url", ignore = true)
	@Mapping(target = "objectDetection", ignore = true)
	@Mapping(target = "objects", ignore = true)
	ImageDto toImageDto(ImageEntity imageEntity);
}
