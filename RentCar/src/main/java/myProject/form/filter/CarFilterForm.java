package myProject.form.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CarFilterForm {
	private String search = "";

	
	private String min = "";
	
	private String max = "";
	
	private int minInt = 0;
	
	private int maxInt = 0;
	
	private static final Pattern p = Pattern.compile("^[0-9]{1}$|^[1-4]{1}[0-9]{1}$|^50$");
	
	private List<Integer> classOfCar = new ArrayList<>();
	private List<String> typeClassOfCar = new ArrayList<>();
	private List<Integer> modelOfCar = new ArrayList<>();
	private List<String> typeModelCar = new ArrayList<>();
	
	private List<Integer> statusCar = new ArrayList<>();

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(p.matcher(min).matches()) minInt = Integer.valueOf(min);
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(p.matcher(max).matches()) maxInt = Integer.valueOf(max);
		this.max = max;
	}

	public int getMinInt() {
		return minInt;
	}

	public void setMinInt(int minInt) {
		this.minInt = minInt;
	}

	public int getMaxInt() {
		return maxInt;
	}

	public void setMaxInt(int maxInt) {
		this.maxInt = maxInt;
	}

	public List<Integer> getModelOfCar() {
		return modelOfCar;
	}

	public void setModelOfCar(List<Integer> modelOfCar) {
		this.modelOfCar = modelOfCar;
	}

	public List<Integer> getStatusCar() {
		return statusCar;
	}
	public void setStatusCar(List<Integer> statusCar) {
		this.statusCar = statusCar;
	}
	public List<Integer> getClassOfCar() {
		return classOfCar;
	}
	public void setClassOfCar(List<Integer> classOfCar) {
		this.classOfCar = classOfCar;
	}
	public List<String> getTypeModelCar() {
		return typeModelCar;
	}
	public void setTypeModelCar(List<String> typeModelCar) {
		this.typeModelCar = typeModelCar;
	}
	public List<String> getTypeClassOfCar() {
		return typeClassOfCar;
	}
	public void setTypeClassOfCar(List<String> typeClassOfCar) {
		this.typeClassOfCar = typeClassOfCar;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
