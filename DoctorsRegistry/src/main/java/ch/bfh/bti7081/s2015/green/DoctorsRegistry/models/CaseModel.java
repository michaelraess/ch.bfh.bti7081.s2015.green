package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import org.neo4j.graphdb.Node;

public class CaseModel extends DefaultModel {
	
	private final String LABEL = "Case";

	public CaseModel() {
		super();
	}

	public void addCase() {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d })", LABEL, nextId);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
}
