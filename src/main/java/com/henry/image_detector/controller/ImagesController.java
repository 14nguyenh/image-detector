package com.henry.image_detector.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.henry.image_detector.controller.beans.ImagesGetResponse;
import com.henry.image_detector.controller.beans.ImagesPostRequest;
import com.henry.image_detector.dto.ImageDto;
import com.henry.image_detector.mapper.ImageMapper;
import com.henry.image_detector.services.ImageService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("images")
@Component("ImagesController")
public class ImagesController {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final ImageMapper imageMapper;
	private final ImageService imageService;

	@Inject
	public ImagesController(final ImageMapper imageMapper, final ImageService imageService) {
		this.imageMapper = imageMapper;
		this.imageService = imageService;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImages(@QueryParam("objects") String objects) {
		LOG.info("Getting list of images and their metadata");
		List<ImageDto> imageDtos = imageService.getImages(objects);
		return Response.ok(new ImagesGetResponse(imageDtos)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{imageId}")
	public Response getImage(@PathParam("imageId") UUID imageId) {
		LOG.info("Getting metadata for image {}", imageId);
		ImageDto image = imageService.getImage(imageId);
		return Response.ok(image).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response detectAndSaveImageObjects(final ImagesPostRequest imagesPostRequest) {
		LOG.info("Running image detect and saving data to db");
		ImageDto imageDto = imageService.detectAndSaveImageObjects(imageMapper.toDto(imagesPostRequest));
		return Response.ok(imageMapper.toImagesPostResponse(imageDto)).build();
	}
}
