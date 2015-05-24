package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import org.neo4j.graphdb.Node;

public class AppointmentModel extends DefaultModel {

	private final String LABEL = "Appointment";
	
	public AppointmentModel(String uri, String user, String password) {
		super(uri, user, password);
	}
	
	public void addAppointment(String dateTime) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, timestamp : '%s' })", LABEL, nextId, dateTime);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	

}
