package myProject.form;

public class UserForm {
	private long id;
	private String login;
	private String password;
	private String fullName;
    private String drivingExperience;
    private String email;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDrivingExperience() {
		return drivingExperience;
	}
	public void setDrivingExperience(String drivingExperience) {
		this.drivingExperience = drivingExperience;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
