package com.Files.FileManager.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class FileManagerServiceImpl implements FileManagerService {

	@Override
	public void delete(String sourceFilePath) throws Exception {
		File sourceFile = new File(sourceFilePath); 
	    if (!sourceFile.delete()) {
	      throw new Exception("Unable to delete file");
	    }
	}

	@Override
	public String download(String downloadUrl) throws MalformedURLException, IOException {
		String username = System. getProperty("user.name");
		String downloadPath = "C:/Users/"+username+"/Downloads/"+getFileName(downloadUrl);
		FileUtils.copyURLToFile(new URL(downloadUrl), new File(downloadPath));
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

	@Override
	public void copy(String sourceFilePath, String destinationFolderPath) throws Exception {
		File sourceFile = new File(sourceFilePath);
	    File destinationFile = new File(destinationFolderPath+"/"+getFileName(sourceFilePath));
	    FileInputStream fileInputStream = new FileInputStream(sourceFile);
		FileOutputStream fileOutputStream = new FileOutputStream(destinationFile);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) > 0) {
           fileOutputStream.write(buffer, 0, length);
        }
        fileInputStream.close();
        fileOutputStream.close();		
	}

}
