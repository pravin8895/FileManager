package com.Files.FileManager.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileManagerController {
	 
	static Logger logger = Logger.getLogger(FileManagerController.class);
	
	/*
	 * delete method is called to delete the given source file
	 * @param sourceFile - the Source file to be deleted
	 * */
	@RequestMapping(value = "/delete", method= RequestMethod.GET)
	public void delete (@RequestParam(value ="sourceFile",required=true) String source) throws Exception {
		logger.info("Delete Method Called");
		File sourceFile = new File(source); 
	    if (sourceFile.delete()) {
	    	logger.info("The File Deleted successfully");
	    } else {
	    	logger.error("Unable to delete file ");
	      throw new Exception("Unable to delete file");
	    } 		
		logger.info("Delete Method Successfully Completed");
	}
	
	/*
	 * download method is called to download the given source file in URL
	 * @param downloadUrl - the URL of the file to be downloaded
	 * */
	@RequestMapping(value = "/download", method= RequestMethod.GET)
	public String download (@RequestParam(value ="downloadUrl",required=true) String downloadUrl) throws MalformedURLException, IOException {
		logger.info("Download Method Called");
		String username = System. getProperty("user.name");
		String downloadPath = "C:/Users/"+username+"/Downloads/"+getFileName(downloadUrl);
		FileUtils.copyURLToFile(new URL(downloadUrl), new File(downloadPath));
		logger.info("Download Method Successfully Completed");
		return downloadPath;
	}
	
	/*
	 * getFileName method is called to split the file name from the given path
	 * @param filePath - the file path from which the name is to be extracted
	 * */
	private String getFileName(String filePath) {
		String[] sourceArray =filePath.split("/");
	    return sourceArray[sourceArray.length-1];
	}
	
	/*
	 * copy method is called to copy the source file to the destination 
	 * @param  source - the source file to be copied
	 * @param  destination - the destination folder where the file is to be pasted
	 * */
	@RequestMapping(value = "/copy", method= RequestMethod.GET)
	public void copy (@RequestParam(value ="sourceFile",required=true) String source,
			@RequestParam(value ="destinationFolder",required=true) String destination) throws Exception {
		logger.info("copy Method Called");
		File sourceFile = new File(source);
	    File destinationFile = new File(destination+"/"+getFileName(source));
	    FileInputStream fileInputStream = new FileInputStream(sourceFile);
		FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) > 0) {
           fileOutputStream.write(buffer, 0, length);
        }
        fileInputStream.close();
        fileOutputStream.close();
        logger.info("copy Method Successfully Completed");
	}
	
}