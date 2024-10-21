package com.henry.image_detector.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

import com.henry.image_detector.exception.ImageDownloadException;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.core.Response;

@Component
public class ImageDownloadClient {
	private final Client client;

	@Inject
	public ImageDownloadClient(final Client client) {
		this.client = client;
	}

	public File downloadImage(String imageUrl, String imageName) {
		try (Response response = client.target(imageUrl).request().get()) {
			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				throw new ImageDownloadException(response.readEntity(String.class));
			}

			File downloadedFile = new File(imageName);

			try (InputStream inputStream = response.readEntity(InputStream.class);
					FileOutputStream outputStream = new FileOutputStream(downloadedFile)) {
				byte[] buffer = new byte[1024];
				int bytesRead;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				return downloadedFile;
			} catch (IOException e) {
				throw new ImageDownloadException(e.getMessage());
			}
		}
	}
}
