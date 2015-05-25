package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class User {
	
	private String email;
	private String password;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User() {
	}
	
	public User(String email, String password) {
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
	
}
