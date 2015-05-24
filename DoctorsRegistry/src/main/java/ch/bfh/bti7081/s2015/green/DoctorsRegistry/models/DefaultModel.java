package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

public class DefaultModel extends RestGraphDatabase {
	
	private final RestCypherQueryEngine cypherQueryEngine;
	
	public static final String DATABASE_ENDPOINT = "http://178.62.254.192:7474/db/data";
    public static final String DATABASE_USERNAME = "neo4j";
    public static final String DATABASE_PASSWORD = "qwerty1";
	
	public DefaultModel(String uri, String user, String password) {
		super(uri, user, password);
		cypherQueryEngine = new RestCypherQueryEngine(this.getRestAPI());
	}
	
	public RestCypherQueryEngine getQueryEngine() {
		return this.cypherQueryEngine;
	}
	
	protected int getLastIdFor(String label) {
		String queryString = String.format("MATCH (n:%s) RETURN max(n.id)", label);
		int crNode = this.getQueryEngine().query(queryString, null).to(Integer.class).single();
		
		return crNode;
	}
	
}
