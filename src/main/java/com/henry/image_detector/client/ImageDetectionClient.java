package com.henry.image_detector.client;

import java.io.File;

import com.henry.image_detector.client.beans.GetTagsResponse;
import com.henry.image_detector.client.beans.UploadImageFileResponse;

public interface ImageDetectionClient {
	UploadImageFileResponse uploadImageFile(File imageFile);

	GetTagsResponse getTagsId(String imageId);

	GetTagsResponse getTagsUrl(String imageUrl);
}
