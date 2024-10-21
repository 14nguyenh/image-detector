package com.henry.image_detector.repository.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "image")
public class ImageEntity {

	@Id
	private UUID uuid;
	private String name;
	private int width;
	private int height;
	private int type;
	private long size;

	@OneToMany(mappedBy = "imageEntity", cascade = CascadeType.ALL)
	private List<DetectedObjectEntity> detectedObjects;

	private ImageEntity(Builder builder) {
		this.uuid = builder.uuid;
		this.name = builder.name;
		this.width = builder.width;
		this.height = builder.height;
		this.type = builder.type;
		this.size = builder.size;
	}

	public ImageEntity() {

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

	public List<DetectedObjectEntity> getDetectedObjects() {
		return detectedObjects;
	}

	public void setDetectedObjects(final List<DetectedObjectEntity> detectedObjects) {
		this.detectedObjects = detectedObjects;
	}

	public static class Builder {
		private UUID uuid;
		private String name;
		private int width;
		private int height;
		private int type;
		private long size;

		public Builder withUuid(UUID uuid) {
			this.uuid = uuid;
			return this;
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder withHeight(int height) {
			this.height = height;
			return this;
		}

		public Builder withType(int type) {
			this.type = type;
			return this;
		}

		public Builder withSize(long size) {
			this.size = size;
			return this;
		}

		public ImageEntity build() {
			return new ImageEntity(this);
		}
	}
}