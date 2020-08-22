package com.Files.FileManager.Service;

import java.io.IOException;
import java.net.MalformedURLException;

public interface FileManagerService {

	public void delete(String sourceFilePath) throws Exception;
	
	public String download(String downloadUrl) throws MalformedURLException, IOException;

	public void copy(String sourceFilePath, String destinationFolderPath) throws Exception;


}
