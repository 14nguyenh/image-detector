package com.henry.image_detector.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.henry.image_detector.repository.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {
	@Query("SELECT a FROM ImageEntity a JOIN a.detectedObjects b WHERE b.name IN :names")
	List<ImageEntity> findImagesByDetectedObjectNames(@Param("names") List<String> names);
}