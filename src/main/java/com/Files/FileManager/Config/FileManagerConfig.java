package com.Files.FileManager.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Files.FileManager.Service.FileManagerService;
import com.Files.FileManager.Service.FileManagerServiceImpl;

@Configuration
public class FileManagerConfig {
	 @Bean
	 public FileManagerService FileManagerService() {
			return new FileManagerServiceImpl();
		}
}
