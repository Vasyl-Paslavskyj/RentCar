package myProject.form.filter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import myProject.service.Implementation.LocalDateTimeParser;

public class HistoryFilterFormForUser {
	private String min = "";
	
	private String max = "";
	
	private LocalDateTime minDT = null;
	
	private LocalDateTime maxDT = null;
	
	private static final Pattern p = Pattern.compile("^(\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}\\:\\d{1,2})$");
	
	private List<Integer> car = new ArrayList<>();
	private List<Integer> modelOfCar = new ArrayList<>();
	private List<Integer> classOfCar = new ArrayList<>();
	private List<String> typeClassOfCar = new ArrayList<>();
	
	private List<Integer> accidentSet = new ArrayList<>();
	private List<Long> id = new ArrayList<>();
	
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

	public List<Integer> getAccidentSet() {
		return accidentSet;
	}

	public void setAccidentSet(List<Integer> accidentSet) {
		this.accidentSet = accidentSet;
	}

	public List<Long> getId() {
		return id;
	}

	public void setId(List<Long> id) {
		this.id = id;
	}
		
}
