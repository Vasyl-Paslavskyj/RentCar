package myProject.service.Implementation;

import java.io.File;
import java.io.IOException;

import myProject.service.FileWriter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileWriterImpl implements FileWriter{

	@Override
	public String write(Folder folder, MultipartFile file, long id) {
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();
			int index = fileName.lastIndexOf(".");
			String extension = fileName.substring(index);
			File pathToHome = new File(System.getProperty("catalina.home"));
			File pathToFolder = new File(pathToHome, "images" + File.separator + folder.name().toLowerCase());
			if(!pathToFolder.exists()){
				pathToFolder.mkdirs();
			}
			File pathToFile = new File(pathToFolder, String.valueOf(id)+extension);
				try {
					file.transferTo(pathToFile);
					return extension;
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
		}
			return null;
	}

}
