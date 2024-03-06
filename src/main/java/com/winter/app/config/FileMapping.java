package com.winter.app.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// ***-context.xml 역할
@Configuration
public class FileMapping implements WebMvcConfigurer{
	
	@Value("${app.upload.url}")
	private String urlPath;			// /files/**
	
	@Value("${app.upload.base}")	// D://upload/
	private String filePath;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler(urlPath)
				.addResourceLocations(filePath);
		
	}
}
