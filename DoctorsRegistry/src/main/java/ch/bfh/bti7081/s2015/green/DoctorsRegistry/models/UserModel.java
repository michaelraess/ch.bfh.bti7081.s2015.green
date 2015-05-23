package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.User;

public class UserModel extends DefaultModel {

	public UserModel(String uri, String user, String password) {
		super(uri, user, password);
	}
	
	public ArrayList<User> getAllUsers(int limit) {
		ArrayList<User> userList = new ArrayList<User>();
		
		Iterable<Node> resUsers = this.getQueryEngine().query("MATCH (n:User) RETURN n", null).to(Node.class);
		
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
		String queryString = String.format("CREATE (n:User { email : '%s', password : '%s' })", email, password);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}

}
