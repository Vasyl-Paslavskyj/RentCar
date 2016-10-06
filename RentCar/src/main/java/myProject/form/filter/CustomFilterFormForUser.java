package myProject.form.filter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import myProject.service.Implementation.LocalDateTimeParser;

public class CustomFilterFormForUser {
	private String search = "";
	
	private String min = "";
	
	private String max = "";
	
	private LocalDateTime minDT = null;
	
	private LocalDateTime maxDT = null;
	
	private static final Pattern p = Pattern.compile("^(\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})$");
	
	private String minCost = "";
	
	private String maxCost = "";
	
	private int minInt = 0;
	
	private int maxInt = 0;
	
	private static final Pattern p2 = Pattern.compile("^\\d{1,5}$");
	
	private List<Integer> car = new ArrayList<>();
	private List<Integer> modelOfCar = new ArrayList<>();
	private List<Integer> classOfCar = new ArrayList<>();
	private List<String> typeClassOfCar = new ArrayList<>();

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		if(p.matcher(min).matches()) minDT = LocalDateTimeParser.localDateTimeParser(min);
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		if(p.matcher(max).matches()) maxDT = LocalDateTimeParser.localDateTimeParser(max);
		this.max = max;
	}

	public LocalDateTime getMinDT() {
		return minDT;
	}

	public void setMinDT(LocalDateTime minDT) {
		this.minDT = minDT;
	}

	public LocalDateTime getMaxDT() {
		return maxDT;
	}

	public void setMaxDT(LocalDateTime maxDT) {
		this.maxDT = maxDT;
	}
		
	public String getMinCost() {
		return minCost;
	}

	public void setMinCost(String minCost) {
		if(p2.matcher(minCost).matches()) minInt = Integer.valueOf(minCost);
		this.minCost = minCost;
	}

	public String getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(String maxCost) {
		if(p2.matcher(maxCost).matches()) maxInt = Integer.valueOf(maxCost);
		this.maxCost = maxCost;
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
	
	public List<Integer> getCar() {
		return car;
	}

	public void setCar(List<Integer> car) {
		this.car = car;
	}

	public List<Integer> getModelOfCar() {
		return modelOfCar;
	}

	public void setModelOfCar(List<Integer> modelOfCar) {
		this.modelOfCar = modelOfCar;
	}
	
	public List<Integer> getClassOfCar() {
		return classOfCar;
	}
	public void setClassOfCar(List<Integer> classOfCar) {
		this.classOfCar = classOfCar;
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
