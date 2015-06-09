package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.helpers.DateTimeConv;

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
			Appointment a = this.resolveAppointmentFields(resAppointment);
			
			if(a != null) {
				appointmentList.add(a);
			}
		}
		
		return appointmentList;
	}
	
	public ArrayList<Appointment> getCaseAppointments(int caseId) {
		ArrayList<Appointment> alAppointments = new ArrayList<Appointment>();
		
		String queryString = String.format("MATCH (a:%s), (c:Case) WHERE c.id=%d AND (c)-[:HAS]-(a) RETURN a", LABEL, caseId);
		Iterable<Node> resAppointments = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resAppointment : resAppointments) {
			Appointment a = this.resolveAppointmentFields(resAppointment);
			
			if(a != null) {
				alAppointments.add(a);
			}
		}
		
		return alAppointments;
	}
	
	private Appointment resolveAppointmentFields(Node n) {
		Appointment a = new Appointment();
		
		if(n.hasProperty("id")) {
			a.setId((int)n.getProperty("id"));
		}
		
		if(n.hasProperty("timestamp")) {
			a.setDateTime((long)n.getProperty("timestamp"));
		}
		
		if(n.hasProperty("descr")) {
			a.setDescr((String)n.getProperty("descr"));
		}
		
		return a;
	}
	
	public void addAppointment(String date, String time, String descr) throws ParseException {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, timestamp : %d, descr : %s })", 
				LABEL, nextId, DateTimeConv.dateTime2Long(date, time), descr);
		
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	

}
