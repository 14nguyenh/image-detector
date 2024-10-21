package com.henry.image_detector.repository.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detectedObject")
public class DetectedObjectEntity {
	@Id
	private UUID uuid;
	private String name;
	private double confidence;

	@ManyToOne
	@JoinColumn(name = "fkImage", referencedColumnName = "uuid")
	private ImageEntity imageEntity;

	public DetectedObjectEntity(Builder builder) {
		this.uuid = builder.uuid;
		this.name = builder.name;
		this.confidence = builder.confidence;
		this.imageEntity = builder.imageEntity;
	}

	public DetectedObjectEntity() {

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

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(final double confidence) {
		this.confidence = confidence;
	}

	public ImageEntity getImageEntity() {
		return imageEntity;
	}

	public void setImageEntity(final ImageEntity imageEntity) {
		this.imageEntity = imageEntity;
	}

	public static class Builder {
		private UUID uuid;
		private String name;
		private double confidence;
		private ImageEntity imageEntity;

		public Builder withUuid(final UUID uuid) {
			this.uuid = uuid;
			return this;
		}

		public Builder withName(final String name) {
			this.name = name;
			return this;
		}

		public Builder withConfidence(final double confidence) {
			this.confidence = confidence;
			return this;
		}

		public Builder withImageEntity(final ImageEntity imageEntity) {
			this.imageEntity = imageEntity;
			return this;
		}

		public DetectedObjectEntity build() {
			return new DetectedObjectEntity(this);
		}
	}
}
