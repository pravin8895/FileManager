package com.Files.FileManager.Controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Files.FileManager.Service.FileManagerService;

@RestController
public class FileManagerController {
	 
	private static Logger logger = Logger.getLogger(FileManagerController.class);
	
	@Autowired
	private FileManagerService fileManagerService;
	
	/*
	 * delete method is called to delete the given source file
	 * @param sourceFile - the Source file to be deleted
	 * */
	@RequestMapping(value = "/delete", method= RequestMethod.GET)
	public void delete (@RequestParam(value ="sourceFile",required=true) String sourceFilePath) throws Exception {
		logger.info("Delete Method Called");
		fileManagerService.delete(sourceFilePath);
		logger.info("Delete Method Successfully Completed");
	}
	
	/*
	 * download method is called to download the given source file in URL
	 * @param downloadUrl - the URL of the file to be downloaded
	 * */
	@RequestMapping(value = "/download", method= RequestMethod.GET)
	public String download (@RequestParam(value ="downloadUrl",required=true) String downloadUrl) throws MalformedURLException, IOException {
		logger.info("Download Method Called");
		String downloadPath = fileManagerService.download(downloadUrl);
		logger.info("Download Method Successfully Completed");
		return downloadPath; 
	}
	
	/*
	 * copy method is called to copy the source file to the destination 
	 * @param  source - the source file to be copied
	 * @param  destination - the destination folder where the file is to be pasted
	 * */
	@RequestMapping(value = "/copy", method= RequestMethod.GET)
	public void copy (@RequestParam(value ="sourceFile",required=true) String sourceFilePath,
			@RequestParam(value ="destinationFolder",required=true) String destinationFolderPath) throws Exception {
		logger.info("copy Method Called");
		fileManagerService.copy(sourceFilePath,destinationFolderPath);
        logger.info("copy Method Successfully Completed");
	}
	
}