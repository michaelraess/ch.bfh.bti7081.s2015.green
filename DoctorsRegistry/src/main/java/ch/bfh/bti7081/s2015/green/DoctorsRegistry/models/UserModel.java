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
		
		String queryString = String.format("MATCH (n:%s) RETURN n", LABEL);
		
		Iterable<Node> resUsers = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resUser : resUsers) {
			User user = new User();
			if(resUser.hasProperty("email")) {
				user.setEmail((String)resUser.getProperty("email"));
			}
			if(resUser.hasProperty("password")) {
				user.setPassword((String)resUser.getProperty("password"));
			}
			userList.add(user);
		}
		
		return userList;
	}
	
	public void addUser(String email, String password) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, email : '%s', password : '%s' })", LABEL, nextId, email, password);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public boolean isLoginCorrect(String email, String password) {
		String queryString = String.format("MATCH (n:%s) WHERE n.email='%s' AND n.password='%s' RETURN COUNT(n)", LABEL, email, password);
		int crNode = this.getQueryEngine().query(queryString, null).to(Integer.class).single();
		
		return crNode == 1 ? true : false;
	}

}
