package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.util.ArrayList;

import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;

public class PatientModel extends DefaultModel {
	
	private final String LABEL = "Patient";

	public PatientModel() {
		super();
	}
	
	public ArrayList<Patient> getAll(int limit) {
		String queryString = String.format("MATCH (n:%s) RETURN n", LABEL);
		Iterable<Node> list = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		return convertToArrayList(list);
	}
	
	public Patient get(int id) {
		String queryString = String.format("MATCH (n:%s) WHERE n.id = %d RETURN n", LABEL, id);
		Iterable<Node> list = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		ArrayList<Patient> patients = convertToArrayList(list);
		
		return patients.get(0);
	}
	
	private ArrayList<Patient> convertToArrayList(Iterable<Node> list) {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		
		for (Node p : list) {
			Patient patient = new Patient();
			
			if (p.hasProperty("id")) {
				patient.setId((int) p.getProperty("id"));
			}
			if (p.hasProperty("firstname")) {
				patient.setFirstname((String) p.getProperty("firstname"));
			}
			if (p.hasProperty("lastname")) {
				patient.setLastname((String) p.getProperty("lastname"));
			}
			if (p.hasProperty("street")) {
				patient.setStreet((String) p.getProperty("street"));
			}
			if (p.hasProperty("zip")) {
				patient.setZip((String) p.getProperty("zip"));
			}
			if (p.hasProperty("place")) {
				patient.setPlace((String) p.getProperty("place"));
			}
			if (p.hasProperty("phone")) {
				patient.setPhone((String) p.getProperty("phone"));
			}
			if (p.hasProperty("mobile")) {
				patient.setMobile((String) p.getProperty("mobile"));
			}
			if (p.hasProperty("email")) {
				patient.setEmail((String) p.getProperty("email"));
			}
			if (p.hasProperty("height")) {
				patient.setHeight((int) p.getProperty("height"));
			}
			if (p.hasProperty("weight")) {
				patient.setWeight((int) p.getProperty("weight"));
			}
			if (p.hasProperty("vegetarian")) {
				patient.setVegetarian(((int) p.getProperty("vegetarian") != 0));
			}
			if (p.hasProperty("smoker")) {
				patient.setSmoker(((int) p.getProperty("smoker") != 0));
			}
			if (p.hasProperty("bloodType")) {
				patient.setBloodType((String) p.getProperty("bloodType"));
			}
			if (p.hasProperty("allergies")) {
				patient.setAllergies((String) p.getProperty("allergies"));
			}
			if (p.hasProperty("intolerances")) {
				patient.setIntolerances((String) p.getProperty("intolerances"));
			}
			if (p.hasProperty("generalNotes")) {
				patient.setGeneralNotes((String) p.getProperty("generalNotes"));
			}
			if (p.hasProperty("biography")) {
				patient.setBiography((String) p.getProperty("biography"));
			}
			
			patients.add(patient);
		}
		
		return patients;
	}
	
	public void add(Patient p) {
		int nextId = this.getLastIdFor(LABEL) + 1;

		String queryString = String.format("CREATE (n:%s "
				+ "{"
					+ "id: %d,"
					+ "firstname: '%s',"
					+ "lastname: '%s',"
					+ "street: '%s',"
					+ "zip: '%s',"
					+ "place: '%s',"
					+ "phone: '%s',"
					+ "mobile: '%s',"
					+ "email: '%s',"
					+ "height: %d,"
					+ "weight: %d,"
					+ "vegetarian: %d,"
					+ "smoker: %d,"
					+ "bloodType: '%s',"
					+ "allergies: '%s',"
					+ "intolerances: '%s',"
					+ "generalNotes: '%s',"
					+ "biography: '%s'"
				+ "})",
				LABEL,
				nextId,
				p.getFirstname(),
				p.getLastname(),
				p.getStreet(),
				p.getZip(),
				p.getPlace(),
				p.getPhone(),
				p.getMobile(),
				p.getEmail(),
				p.getHeight(),
				p.getWeight(),
				(p.getVegetarian()) ? 1 : 0,
				(p.getSmoker()) ? 1 : 0,
				p.getBloodType(),
				p.getAllergies(),
				p.getIntolerances(),
				p.getGeneralNotes(),
				p.getBiography()
				
		);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public void update(Patient p) {
		String queryString = String.format("MATCH (n:%s { id: %d }) SET "
				+ "n.firstname = '%s',"
				+ "n.lastname = '%s',"
				+ "n.street = '%s',"
				+ "n.zip = '%s',"
				+ "n.place = '%s',"
				+ "n.phone = '%s',"
				+ "n.mobile = '%s',"
				+ "n.email = '%s',"
				+ "n.height = %d,"
				+ "n.weight = %d,"
				+ "n.vegetarian = %d,"
				+ "n.smoker = %d,"
				+ "n.bloodType = '%s',"
				+ "n.allergies = '%s',"
				+ "n.intolerances = '%s',"
				+ "n.generalNotes = '%s',"
				+ "n.biography = '%s'",
				LABEL,
				p.getId(),
				p.getFirstname(),
				p.getLastname(),
				p.getStreet(),
				p.getZip(),
				p.getPlace(),
				p.getPhone(),
				p.getMobile(),
				p.getEmail(),
				p.getHeight(),
				p.getWeight(),
				(p.getVegetarian()) ? 1 : 0,
				(p.getSmoker()) ? 1 : 0,
				p.getBloodType(),
				p.getAllergies(),
				p.getIntolerances(),
				p.getGeneralNotes(),
				p.getBiography()
		);
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}
	
	public void delete(Patient p) {
		
		String queryString = String.format("MATCH (n: %s) WHERE n.id = %d DELETE n", LABEL, p.getId());
		this.getQueryEngine().query(queryString, null).to(Node.class);
	}

}
