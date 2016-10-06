package myProject.form;

import org.springframework.web.multipart.MultipartFile;

import myProject.models.ClassOfCar;

public class ModelOfCarForm {
	
	private long id;
	
	private ClassOfCar classOfCar;
	
	private String typeModelCar;
	
	private int version;
    
    private String path;
    
    private MultipartFile file;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClassOfCar getClassOfCar() {
		return classOfCar;
	}

	public void setClassOfCar(ClassOfCar classOfCar) {
		this.classOfCar = classOfCar;
	}

	public String getTypeModelCar() {
		return typeModelCar;
	}

	public void setTypeModelCar(String typeModelCar) {
		this.typeModelCar = typeModelCar;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
