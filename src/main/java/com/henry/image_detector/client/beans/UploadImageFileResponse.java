package com.henry.image_detector.client.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadImageFileResponse {
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
		@JsonProperty("upload_id")
		private String uploadId;

		public String getUploadId() {
			return uploadId;
		}

		public void setUploadId(final String uploadId) {
			this.uploadId = uploadId;
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
