package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class User {
	
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private int id;
	private boolean isdoctor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User() {
		this.setDoctor(false);
	}
	
	public User(String email, String password) {
		this();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Id: " + this.id + "E-Mail: " + this.email + ";Password: " + this.password;
	}

	public boolean isDoctor() {
		return isdoctor;
	}
	
	public String getIsDoctorString() {
		return isdoctor ? "yes" : "no";
	}

	public void setDoctor(boolean isDoctor) {
		this.isdoctor = isDoctor;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
}
