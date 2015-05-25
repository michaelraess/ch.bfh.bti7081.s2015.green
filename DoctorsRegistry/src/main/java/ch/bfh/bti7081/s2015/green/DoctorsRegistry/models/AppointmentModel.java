package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;

public class AppointmentModel extends DefaultModel {

	private final String LABEL = "Appointment";
	
	public AppointmentModel() {
		super();
	}
	
	public ArrayList<Appointment> getNextAppointments(int limit) {
		ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
		
		String queryString = String.format("MATCH (n:%s) RETURN n ORDER BY n.timestamp LIMIT %d", LABEL, limit);
		
		Iterable<Node> resAppointments = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resAppointment : resAppointments) {
			Appointment user = new Appointment();
			if(resAppointment.hasProperty("timestamp")) {
				user.setDateTime((long)resAppointment.getProperty("timestamp"));
			}
			
			if(resAppointment.hasProperty("id")) {
				user.setId((int)resAppointment.getProperty("id"));
			}
			
			if(resAppointment.hasProperty("case_id")) {
				user.setCaseId((int)resAppointment.getProperty("case_id"));
			}
			
			appointmentList.add(user);
		}
		
		return appointmentList;
	}
	
	public void addAppointment(long dateTime, int caseId) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, timestamp : '%d', case_id : %d })", LABEL, nextId, dateTime, caseId);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	

}
