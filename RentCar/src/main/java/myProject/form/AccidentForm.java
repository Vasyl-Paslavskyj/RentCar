package myProject.form;

import java.util.HashSet;
import java.util.Set;

import myProject.models.Custom;

public class AccidentForm {
	
	private long id;
	
	private String dateTime;
    
	private String damage;
    
	private String wastage;
	
	private Set<Custom> customSet = new HashSet<Custom>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public Set<Custom> getCustomSet() {
		return customSet;
	}

	public void setCustomSet(Set<Custom> customSet) {
		this.customSet = customSet;
	}

	public String getWastage() {
		return wastage;
	}

	public void setWastage(String wastage) {
		this.wastage = wastage;
	}
	
}
