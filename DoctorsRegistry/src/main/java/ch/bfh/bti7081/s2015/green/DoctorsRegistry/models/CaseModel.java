package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import org.neo4j.graphdb.Node;

public class CaseModel extends DefaultModel {
	
	private final String LABEL = "Case";

	public CaseModel() {
		super();
	}

	public void addCase() {
		String queryString = String.format("CREATE (n:%s)", LABEL);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
}
