package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Medication;

public class MedicationModel extends DefaultModel {
	
	private final String LABEL = "Medication";

	public MedicationModel() {
		super();
	}
	
	public void addMedication(String name, String descr, int dayDose) {
		int nextId = this.getLastIdFor(LABEL) + 1;
		
		String queryString = String.format("CREATE (n:%s { id : %d, name : '%s', descr : '%s', day_dose : %d})", 
				LABEL, nextId, name, descr, dayDose);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public ArrayList<Medication> getAllMedications(int limit) {
		ArrayList<Medication> userList = new ArrayList<Medication>();
		
		String queryString = String.format("MATCH (n:%s) RETURN n LIMIT %d", LABEL, limit);
		
		Iterable<Node> resMedics = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		for(Node resMedic : resMedics) {
			Medication medic = this.resolveMedicationFields(resMedic);
			if(medic != null) {
				userList.add(medic);
			}
		}
		
		return userList;
	}
	
	private Medication resolveMedicationFields(Node resMedic) {
		Medication medic = new Medication();
		
		if(resMedic.hasProperty("id")) {
			medic.setId((int)resMedic.getProperty("id"));
		}
		if(resMedic.hasProperty("name")) {
			medic.setName((String)resMedic.getProperty("name"));
		}
		if(resMedic.hasProperty("descr")) {
			medic.setDescr((String)resMedic.getProperty("descr"));
		}
		if(resMedic.hasProperty("day_dose")) {
			medic.setDayDose((int)resMedic.getProperty("day_dose"));
		}
		return medic;
	}

}
