package com.henry.image_detector.client.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetTagsResponse {
	private Result result;
	private Status status;

	public Result getResult() {
		return result;
	}

	public void setResult(final Result result) {
		this.result = result;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(final Status status) {
		this.status = status;
	}

	public static class Result {
		private List<TagConfidence> tags;

		// Getters and Setters
		public List<TagConfidence> getTags() {
			return tags;
		}

		public void setTags(final List<TagConfidence> tags) {
			this.tags = tags;
		}
	}

	public static class TagConfidence {
		private double confidence;
		private Tag tag;

		public double getConfidence() {
			return confidence;
		}

		public void setConfidence(final double confidence) {
			this.confidence = confidence;
		}

		public Tag getTag() {
			return tag;
		}

		public void setTag(final Tag tag) {
			this.tag = tag;
		}
	}

	public static class Tag {
		private String en;

		public String getEn() {
			return en;
		}

		public void setEn(final String en) {
			this.en = en;
		}
	}

	public static class Status {
		private String text;
		private String type;

		public String getText() {
			return text;
		}

		public void setText(final String text) {
			this.text = text;
		}

		public String getType() {
			return type;
		}

		public void setType(final String type) {
			this.type = type;
		}
	}
}
