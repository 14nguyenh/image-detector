package com.henry.image_detector.controller.beans;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.henry.image_detector.dto.DetectedObjectDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagesPostResponse {
	private UUID uuid;
	private String name;
	private int width;
	private int height;
	private int type;
	private long size;
	List<DetectedObjectDto> objects;

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
