package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.PatientMedication;

public class PatientMedicationModel extends DefaultModel {
	
	private final String LABEL = "PatientMedication";
	
	public PatientMedicationModel() {
		super();
	}
	
	public ArrayList<PatientMedication> getMedicationForAppointment(int appntmnId) {
		ArrayList<PatientMedication> alPmm = new ArrayList<PatientMedication>();
		
		String queryString = String.format("MATCH (pm:%s) WHERE pm.appointmentId = %d RETURN pm", 
				LABEL, appntmnId);
		
		Iterable<Node> resMedications = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resMedication : resMedications) {
			PatientMedication pm = this.resolveMedicationFields(resMedication);
			
			if(pm != null) {
				alPmm.add(pm);
			}
		}
		
		return alPmm;
	}
	
	private PatientMedication resolveMedicationFields(Node n) {
		PatientMedication a = new PatientMedication();
		
		if(n.hasProperty("id")) {
			a.setId((int)n.getProperty("id"));
		}
		
		if(n.hasProperty("name")) {
			a.setName((String)n.getProperty("name"));
		}
		
		if(n.hasProperty("dose")) {
			a.setDose((int)n.getProperty("dose"));
		}
		
		if(n.hasProperty("appointmentId")) {
			a.setAppointmentId((int)n.getProperty("appointmentId"));
		}
		
		return a;
	}

	public void addMedicationForAppointment(int appntmnId, String name, int dayDose) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (pm:%s {id : %d, name : '%s', dose : %d, appointmentId : %d})", 
				LABEL, nextId, name, dayDose, appntmnId);
		
		this.getQueryEngine().query(queryString, null).to(Node.class);
		
	}
	
}
