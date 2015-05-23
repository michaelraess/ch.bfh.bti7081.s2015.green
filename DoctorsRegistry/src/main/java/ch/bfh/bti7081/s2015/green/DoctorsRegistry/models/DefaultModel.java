package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;

public class DefaultModel extends RestGraphDatabase {
	
	private final RestCypherQueryEngine cypherQueryEngine;
	
	public DefaultModel(String uri, String user, String password) {
		super(uri, user, password);
		cypherQueryEngine = new RestCypherQueryEngine(this.getRestAPI());
	}
	
	public RestCypherQueryEngine getQueryEngine() {
		return this.cypherQueryEngine;
	}
	
}
