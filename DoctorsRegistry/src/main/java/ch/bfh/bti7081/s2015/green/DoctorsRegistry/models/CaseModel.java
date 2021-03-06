package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;
import java.util.Date;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Appointment;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Case;

public class CaseModel extends DefaultModel {
	
	private final String LABEL = "Case";

	public CaseModel() {
		super();
	}

	public int addCase() {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, timestamp : %d})", 
				LABEL, nextId, (new Date()).getTime());
		this.getQueryEngine().query(queryString, null).to(Node.class);
		
		return nextId;
	}
	
	public void attachPatient(int caseId, int patientId) {
		String queryString = String.format("MATCH (c:Case),(p:Patient) WHERE c.id=%d AND p.id=%d "
				+ "CREATE (c)-[f:FOR]->(p)", 
				caseId, patientId);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public void attachDoctor(int caseId, int doctorId) {
		String queryString = String.format("MATCH (c:Case),(u:User) WHERE c.id=%d AND u.id=%d "
				+ "CREATE (c)-[f:HELD_BY]->(u)", 
				caseId, doctorId);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public ArrayList<Case> getAllCases(int limit) {
		ArrayList<Case> alCases = new ArrayList<Case>();
		
		String queryString = String.format("MATCH (n:%s) RETURN n LIMIT %d", LABEL, limit);
		Iterable<Node> list = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node n : list) {
			Case c = this.resolveCaseFields(n);
			
			if(c != null) {
				alCases.add(c);
			}
		}
		
		return alCases;
	}

	private Case resolveCaseFields(Node n) {
		Case c = new Case();
		
		if (n.hasProperty("id")) {
			c.setId((int) n.getProperty("id"));
		}
		
		if (n.hasProperty("timestamp")) {
			c.setTimestamp((long) n.getProperty("timestamp"));
		}
		
		//Retrieve all appointments for this case
		AppointmentModel am = new AppointmentModel();
		ArrayList<Appointment> alAppntmnt = am.getCaseAppointments(c.getId());
		
		c.setAlAppnmt(alAppntmnt);
		
		//Retrieve a patient for this case
		PatientModel pm = new PatientModel();
		c.setPatient(pm.getCasePatient(c.getId()));
		
		//Retrieve a doctor for this case
		UserModel um = new UserModel();
		c.setDoctor(um.getCaseDoctor(c.getId()));
		
		return c;
	}
}
