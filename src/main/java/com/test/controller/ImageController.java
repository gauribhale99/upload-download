package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.entity.Image;
import com.test.repository.ImageRepository;

@RestController
public class ImageController {

	@Autowired
	private ImageRepository repository;
	
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) throws IOException
	{
		Map<String,String> response=new HashMap();
		Image image=new Image();
		image.setName(file.getOriginalFilename());
		image.setContent(file.getBytes());
		image.setType(file.getContentType());
		response.put("message", "file uploaded successfully");
		this.repository.save(image);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/download")
	public ResponseEntity<?> downloadFile(@RequestParam("image") String file) 
	{
		Map<String,String> response=new HashMap();
		Optional<Image> image=this.repository.findByName(file);
		if(image.isPresent())
		{
			return new ResponseEntity<>(image.get(),HttpStatus.OK);
		}
		else
		{
			response.put("message", "image not found!!");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
	}
	
}
