package com.Files.FileManager;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.Files.FileManager.Config.FileManagerConfig;

@SpringBootApplication
@Import(FileManagerConfig.class)
public class FileManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagerApplication.class, args);
		BasicConfigurator.configure();
	}

}
