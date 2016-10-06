package myProject.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {
	enum Folder{
		MODELOFCAR;
	}
	
	String write(Folder folder, MultipartFile file, long id);
}
