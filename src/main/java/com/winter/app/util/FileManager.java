package com.winter.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public String fileSave(String path, MultipartFile multipartFile) throws Exception{
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		// 1. 파일 이름
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+multipartFile.getOriginalFilename();
		
		// 2. 파일 저장
		file = new File(file, fileName);
		//   a. multipartFile transferTo 메서드
		multipartFile.transferTo(file);

		//   b. FileCopyUtils copy 메서드
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
		
		
		return fileName;
	}
	
}
