package com.henry.image_detector.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageDto {
	private String dataType;
	private String data;
	private String fileName;
	private String url;
	private boolean objectDetection;

	//metadata
	private UUID uuid;
	private String name;
	private int width;
	private int height;
	private int type;
	private long size;

	//detected objects
	List<DetectedObjectDto> objects;

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

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(final UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(final int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(final int height) {
		this.height = height;
	}

	public int getType() {
		return type;
	}

	public void setType(final int type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(final long size) {
		this.size = size;
	}

	public List<DetectedObjectDto> getObjects() {
		return objects;
	}

	public void setObjects(final List<DetectedObjectDto> objects) {
		this.objects = objects;
	}
}
