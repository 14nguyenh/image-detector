package com.henry.image_detector.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.henry.image_detector.repository.entity.DetectedObjectEntity;

public interface DetectedObjectRepository extends JpaRepository<DetectedObjectEntity, UUID> {
	
}
