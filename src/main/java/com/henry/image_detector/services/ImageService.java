package com.henry.image_detector.services;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.henry.image_detector.client.ImageDetectionClient;
import com.henry.image_detector.client.ImageDownloadClient;
import com.henry.image_detector.client.beans.GetTagsResponse;
import com.henry.image_detector.client.beans.UploadImageFileResponse;
import com.henry.image_detector.dto.DetectedObjectDto;
import com.henry.image_detector.dto.ImageDto;
import com.henry.image_detector.exception.ImageBase64ConversionException;
import com.henry.image_detector.exception.ImageNotFoundException;
import com.henry.image_detector.exception.MetadataReadException;
import com.henry.image_detector.mapper.ImageMapper;
import com.henry.image_detector.repository.DetectedObjectRepository;
import com.henry.image_detector.repository.ImageRepository;
import com.henry.image_detector.repository.entity.DetectedObjectEntity;
import com.henry.image_detector.repository.entity.ImageEntity;

import jakarta.inject.Inject;

@Service
public class ImageService {
	private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private final ImageDetectionClient imageDetectionClient;
	private final ImageDownloadClient imageDownloadClient;
	private final ImageRepository imageRepository;
	private final DetectedObjectRepository objectRepository;
	private final ImageMapper imageMapper;

	@Inject
	public ImageService(final ImageDetectionClient imageDetectionClient, final ImageDownloadClient imageDownloadClient, final ImageRepository imageRepository,
			final DetectedObjectRepository detectedObjectRepository, final ImageMapper imageMapper) {
		this.imageDetectionClient = imageDetectionClient;
		this.imageDownloadClient = imageDownloadClient;
		this.imageRepository = imageRepository;
		this.objectRepository = detectedObjectRepository;
		this.imageMapper = imageMapper;
	}

	public ImageDto getImage(UUID imageId) {
		Optional<ImageEntity> image = imageRepository.findById(imageId);
		if (!image.isPresent()) {
			LOG.info("Image not found: {}", imageId);
			throw new ImageNotFoundException("Image not found: " + imageId);
		}
		return imageMapper.toImageDto(image.get());
	}

	public List<ImageDto> getImages(final String objects) {
		List<ImageEntity> imageEntities;

		if (!StringUtils.isEmpty(objects)) {
			LOG.info("Query param provided, searching for images with objects: {}", objects);
			List<String> objectsQuery = Arrays.asList(objects.split(","));
			imageEntities = imageRepository.findImagesByDetectedObjectNames(objectsQuery);
		} else {
			LOG.info("No query param, getting all images");
			imageEntities = imageRepository.findAll();
		}

		List<ImageDto> imageDtos = new ArrayList<>();
		for (ImageEntity imageEntity : imageEntities) {
			imageDtos.add(imageMapper.toImageDto(imageEntity));
		}

		return imageDtos;
	}

	@Transactional
	public ImageDto detectAndSaveImageObjects(final ImageDto image) {
		GetTagsResponse tags = null;
		UUID imageId = UUID.randomUUID();
		String imageName = StringUtils.EMPTY;
		ImageEntity imageEntity = null;
		List<DetectedObjectEntity> detectedObjectEntities = null;
		if (StringUtils.isNotEmpty(image.getDataType()) && StringUtils.isNotEmpty(image.getData())) {
			LOG.info("Base64 image provided, doing file upload detection");
			imageName = image.getFileName();
			if (StringUtils.isEmpty(imageName)) {
				imageName = imageId + "." + image.getDataType();
			}

			try {
				File imageFile = convertBytesToFile(Base64.getDecoder().decode(image.getData()), imageName);
				imageEntity = saveMetadata(imageFile, imageId);
				UploadImageFileResponse response = imageDetectionClient.uploadImageFile(imageFile);
				tags = imageDetectionClient.getTagsId(response.getResult().getUploadId());
			} catch (IOException e) {
				throw new ImageBase64ConversionException("Error converting bytes to file", e);
			}
		} else if (StringUtils.isNotEmpty(image.getUrl())) {
			LOG.info("URL provided, doing detection on url provided image");
			imageName = getImageName(image.getUrl());
			File imageFile = imageDownloadClient.downloadImage(image.getUrl(), imageName);
			imageEntity = saveMetadata(imageFile, imageId);
			tags = imageDetectionClient.getTagsUrl(image.getUrl());
		}

		if (tags != null && image.isObjectDetection()) {
			LOG.info("Object detection enabled");
			detectedObjectEntities = saveImageObjects(tags.getResult().getTags(), imageId, imageEntity);
		}

		return toDto(imageEntity, detectedObjectEntities, imageId, imageName);
	}

	private File convertBytesToFile(byte[] bytes, String fileName) throws IOException {
		File file = new File(fileName);
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(bytes);
		}
		return file;
	}

	private String getImageName(String imageUrl) {
		return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
	}

	private ImageEntity saveMetadata(final File imageFile, final UUID imageId) {
		try {
			BufferedImage image = ImageIO.read(imageFile);
			ImageEntity imageEntity = new ImageEntity.Builder()
					.withUuid(imageId)
					.withWidth(image.getWidth())
					.withHeight(image.getHeight())
					.withType(image.getType())
					.withSize(imageFile.getAbsoluteFile().length())
					.build();

			return imageRepository.save(imageEntity);
		} catch (IOException e) {
			throw new MetadataReadException(e.getMessage());
		} finally {
			LOG.info("Saved image metadata");
		}
	}

	private List<DetectedObjectEntity> saveImageObjects(final List<GetTagsResponse.TagConfidence> tagConfidences, final UUID imageId, final ImageEntity imageEntity) {
		List<DetectedObjectEntity> detectedObjects = new ArrayList<>();
		for (GetTagsResponse.TagConfidence tagConfidence : tagConfidences) {
			String objectDetected = tagConfidence.getTag().getEn();
			double confidence = tagConfidence.getConfidence();

			DetectedObjectEntity detectedObjectEntity = new DetectedObjectEntity.Builder()
					.withUuid(UUID.randomUUID())
					.withName(objectDetected)
					.withConfidence(confidence)
					.withImageEntity(imageEntity)
					.build();

			detectedObjects.add(objectRepository.save(detectedObjectEntity));
		}
		LOG.info("Saved all objects detected in image");
		return detectedObjects;
	}

	private ImageDto toDto(final ImageEntity imageEntity, final List<DetectedObjectEntity> detectedObjectEntity, final UUID imageId, final String imageName) {
		ImageDto imageDto = new ImageDto();

		if (imageEntity != null) {
			imageDto.setUuid(imageId);
			imageDto.setName(imageName);
			imageDto.setWidth(imageEntity.getWidth());
			imageDto.setHeight(imageEntity.getHeight());
			imageDto.setType(imageEntity.getType());
			imageDto.setSize(imageEntity.getSize());
		}

		if (detectedObjectEntity != null) {
			List<DetectedObjectDto> objects = new ArrayList<>();
			for (DetectedObjectEntity detectedObject : detectedObjectEntity) {
				DetectedObjectDto objectDto = new DetectedObjectDto();
				objectDto.setName(detectedObject.getName());
				objectDto.setConfidence(detectedObject.getConfidence());
				objects.add(objectDto);
			}
			imageDto.setObjects(objects);
		}

		return imageDto;
	}
}
