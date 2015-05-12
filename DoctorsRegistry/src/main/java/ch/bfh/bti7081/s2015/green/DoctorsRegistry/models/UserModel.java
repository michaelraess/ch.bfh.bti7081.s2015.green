package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;
import java.util.List;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.entity.User;

public class UserModel {
	
	List<User> users = new ArrayList<User>();
	
	public UserModel() {
		this.load();
	}
	
	//Loading Database
	public void load() {
		
	}
	
	//Saving Database
	public void save() {
		
	}
	
	//Get all users
	public ArrayList<User> getAll() {
		return (ArrayList<User>) this.users;
	}
	
	//
	public void addUser(String[] pp) {
		//User user = new User(id, email, password_hash)
		//this.users.add(user);
	}
}
