package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;

public class UserModel extends DefaultModel {
	
	private final String LABEL = "User";
	
	public UserModel() {
		super();
	}
	
	public ArrayList<User> getAllUsers(int limit) {
		ArrayList<User> userList = new ArrayList<User>();
		
		String queryString = String.format("MATCH (n:%s) RETURN n LIMIT %d", LABEL, limit);
		
		Iterable<Node> resUsers = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resUser : resUsers) {
			User user = this.resolveUserFields(resUser);
			if(user != null) {
				userList.add(user);
			}
		}
		
		return userList;
	}
	
	public ArrayList<User> getAllDoctors() {
		ArrayList<User> userList = new ArrayList<User>();
		
		String queryString = String.format("MATCH (n:%s) WHERE n.is_doctor = %b RETURN n", LABEL, true);
		
		Iterable<Node> resUsers = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resUser : resUsers) {
			User user = this.resolveUserFields(resUser);
			if(user != null) {
				userList.add(user);
			}
		}
		
		return userList;
	}
	
	public User getCaseDoctor(int caseId) {
		String queryString = String.format("MATCH (p:%s),(c:Case) WHERE c.id=%d AND (c:Case)-[:HELD_BY]->(p) RETURN p", LABEL, caseId);
		Node cpNode = this.getQueryEngine().query(queryString, null).to(Node.class).single();
		
		return this.resolveUserFields(cpNode);
	}
	
	private User resolveUserFields(Node resUser) {
		User user = new User();
		
		if(resUser.hasProperty("id")) {
			user.setId((int)resUser.getProperty("id"));
		}
		if(resUser.hasProperty("email")) {
			user.setEmail((String)resUser.getProperty("email"));
		}
		if(resUser.hasProperty("password")) {
			user.setPassword((String)resUser.getProperty("password"));
		}
		if(resUser.hasProperty("is_doctor")) {
			user.setDoctor((boolean)resUser.getProperty("is_doctor"));
		}
		if(resUser.hasProperty("first_name")) {
			user.setFirstName((String)resUser.getProperty("first_name"));
		}
		if(resUser.hasProperty("last_name")) {
			user.setLastName((String)resUser.getProperty("last_name"));
		}
		return user;
	}
	
	public void addUser(String email, String password, String first_name, String last_name, boolean isDoctor) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, email : '%s', password : '%s', first_name : %s, last_name : %s, is_doctor : %b })", 
				LABEL, nextId, email, password, first_name, last_name, isDoctor);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public void deleteUser(User user) {
		
		String queryString = String.format("MATCH (n:%s) WHERE n.email='%s' AND n.password='%s' \n DELETE n ", LABEL, user.getEmail(), user.getPassword());
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public boolean isLoginCorrect(String email, String password) {
		String queryString = String.format("MATCH (n:%s) WHERE n.email='%s' AND n.password='%s' RETURN COUNT(n)", LABEL, email, password);
		int crNode = this.getQueryEngine().query(queryString, null).to(Integer.class).single();
		
		return crNode == 1 ? true : false;
	}

}
