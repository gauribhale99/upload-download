package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	
	public Optional<Image> findByName(String file);

}
