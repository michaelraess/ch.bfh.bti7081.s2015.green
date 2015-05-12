package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.entity;

public class User {
	
	private String id = null;
	private String email = null;
	private String first_name = null;
	private String last_name = null;
	private String password_hash = null;
	
	public User() {
		
	}
	
	public User(String id, String email, String password_hash) {
		this.id = id;
		this.email = email;
		this.password_hash = password_hash;
	}

}
