package com.henry.image_detector.client;

import java.io.File;
import java.io.IOException;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.springframework.stereotype.Component;

import com.henry.image_detector.client.beans.GetTagsResponse;
import com.henry.image_detector.client.beans.UploadImageFileResponse;
import com.henry.image_detector.config.ImageDetectionConfig;
import com.henry.image_detector.exception.ImageDetectionException;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Component
public class ImaggaImageDetectionClient implements ImageDetectionClient {
	private static final String UPLOAD_PATH = "/v2/uploads";
	private static final String TAG_PATH = "/v2/tags";

	private final Client client;
	private final ImageDetectionConfig config;

	@Inject
	public ImaggaImageDetectionClient(final Client client, final ImageDetectionConfig config) {
		this.client = client;
		this.config = config;
	}

	@Override
	public UploadImageFileResponse uploadImageFile(final File imageFile) {
		try (FormDataMultiPart multiPart = new FormDataMultiPart()) {
			FileDataBodyPart filePart = new FileDataBodyPart("image", imageFile, MediaType.APPLICATION_OCTET_STREAM_TYPE);
			multiPart.bodyPart(filePart);

			try (Response response = client.target(config.getApiEndpoint())
					.path(UPLOAD_PATH)
					.request(MediaType.APPLICATION_JSON_TYPE)
					.header("Authorization", config.getBasicAuthKey())
					.post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA))) {

				if (response.getStatus() != Response.Status.OK.getStatusCode()) {
					throw new ImageDetectionException(response.readEntity(String.class));
				}

				return response.readEntity(UploadImageFileResponse.class);
			}
		} catch (IOException e) {
			throw new ImageDetectionException(e.getMessage());
		}
	}

	@Override
	public GetTagsResponse getTagsId(final String imageId) {
		try (Response response = client.target(config.getApiEndpoint())
				.path(TAG_PATH)
				.queryParam("image_upload_id", imageId)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", config.getBasicAuthKey())
				.get()) {

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				throw new ImageDetectionException(response.readEntity(String.class));
			}

			return response.readEntity(GetTagsResponse.class);
		}
	}

	@Override
	public GetTagsResponse getTagsUrl(final String imageId) {
		try (Response response = client.target(config.getApiEndpoint())
				.path(TAG_PATH)
				.queryParam("image_url", imageId)
				.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", config.getBasicAuthKey())
				.get()) {

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				throw new ImageDetectionException(response.readEntity(String.class));
			}

			return response.readEntity(GetTagsResponse.class);
		}
	}
}
