package myProject.form;

import myProject.models.Car;
import myProject.models.City;
import myProject.models.User;

public class CustomForm {
	private long id;
	
	private String dateTimeStart;
	
	private City cityStart;
	
	private String dateTimeFinish;
	
	private City cityFinish;
	
	private Car car;
	
	private User user;
	
	private String rentalLength;
	
    private String cost;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(String dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public City getCityStart() {
		return cityStart;
	}

	public void setCityStart(City cityStart) {
		this.cityStart = cityStart;
	}

	public String getDateTimeFinish() {
		return dateTimeFinish;
	}

	public void setDateTimeFinish(String dateTimeFinish) {
		this.dateTimeFinish = dateTimeFinish;
	}

	public City getCityFinish() {
		return cityFinish;
	}

	public void setCityFinish(City cityFinish) {
		this.cityFinish = cityFinish;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String getRentalLength() {
		return rentalLength;
	}

	public void setRentalLength(String rentalLength) {
		this.rentalLength = rentalLength;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
