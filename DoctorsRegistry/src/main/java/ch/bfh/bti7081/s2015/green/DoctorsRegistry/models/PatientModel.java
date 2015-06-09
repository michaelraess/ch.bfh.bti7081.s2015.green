package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.commons.beanutils.PropertyUtils;
import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoId;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoProperty;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.helpers.neoActions;

public class PatientModel extends DefaultModel implements neoActions<Patient> {
	
	private final String LABEL = "Patient";


	public PatientModel() {
		super();
	}


	@Override
	public void save(Patient entity) {
		
		String queryString = String.format("MERGE (n:%s { ", LABEL);

		for (Field field : Patient.class.getDeclaredFields()) {
			if (field.isAnnotationPresent(neoId.class)) {
				try {
					Object value = PropertyUtils.getProperty(entity, "id");
					queryString += " " + field.getName() + " : " + value + ", ";
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (field.isAnnotationPresent(neoProperty.class)) {
				try {
					Object value = PropertyUtils.getProperty(entity, field.getName());
					queryString += ", " + field.getName() + " : " + value + " ";
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		queryString += " })";
		
		this.getQueryEngine().query(queryString, null).to(Node.class);
		
		System.out.println(queryString);
	}

	@Override
	public void remove(Patient entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient findAll(int limit) {
		// TODO Auto-generated method stub
		return null;
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
	
	public ArrayList<Patient> getAllWithoutCase() {
		String queryString = String.format("MATCH (p:%s) WHERE NOT ()-[:FOR]->(p) RETURN p", LABEL);
		Iterable<Node> list = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		return convertToArrayList(list);
	}
	
	public ArrayList<Patient> getAllWithCase() {
		String queryString = String.format("MATCH (p:%s) WHERE ()-[:FOR]->(p) RETURN p", LABEL);
		Iterable<Node> list = this.getQueryEngine().query(queryString, null).to(Node.class);
		
		return convertToArrayList(list);
	}
	
	public Patient getCasePatient(int caseId) {
		String queryString = String.format("MATCH (p:%s),(c:Case) WHERE c.id=%d AND (c:Case)-[:FOR]->(p) RETURN p", LABEL, caseId);
		Node cpNode = this.getQueryEngine().query(queryString, null).to(Node.class).single();
		
		return this.resolvePatientFields(cpNode);
	}
	
	private Patient resolvePatientFields(Node n) {
		Patient patient = new Patient();
		
		if (n.hasProperty("id")) {
			patient.setId((int) n.getProperty("id"));
		}
		if (n.hasProperty("firstname")) {
			patient.setFirstname((String) n.getProperty("firstname"));
		}
		if (n.hasProperty("lastname")) {
			patient.setLastname((String) n.getProperty("lastname"));
		}
		if (n.hasProperty("street")) {
			patient.setStreet((String) n.getProperty("street"));
		}
		if (n.hasProperty("zip")) {
			patient.setZip((String) n.getProperty("zip"));
		}
		if (n.hasProperty("place")) {
			patient.setPlace((String) n.getProperty("place"));
		}
		if (n.hasProperty("phone")) {
			patient.setPhone((String) n.getProperty("phone"));
		}
		if (n.hasProperty("mobile")) {
			patient.setMobile((String) n.getProperty("mobile"));
		}
		if (n.hasProperty("email")) {
			patient.setEmail((String) n.getProperty("email"));
		}
		if (n.hasProperty("height")) {
			patient.setHeight((int) n.getProperty("height"));
		}
		if (n.hasProperty("weight")) {
			patient.setWeight((int) n.getProperty("weight"));
		}
		if (n.hasProperty("vegetarian")) {
			patient.setVegetarian(((int) n.getProperty("vegetarian") != 0));
		}
		if (n.hasProperty("smoker")) {
			patient.setSmoker(((int) n.getProperty("smoker") != 0));
		}
		if (n.hasProperty("bloodType")) {
			patient.setBloodType((String) n.getProperty("bloodType"));
		}
		if (n.hasProperty("allergies")) {
			patient.setAllergies((String) n.getProperty("allergies"));
		}
		if (n.hasProperty("intolerances")) {
			patient.setIntolerances((String) n.getProperty("intolerances"));
		}
		if (n.hasProperty("generalNotes")) {
			patient.setGeneralNotes((String) n.getProperty("generalNotes"));
		}
		if (n.hasProperty("biography")) {
			patient.setBiography((String) n.getProperty("biography"));
		}
		
		return patient;
	}

	private ArrayList<Patient> convertToArrayList(Iterable<Node> list) {
		ArrayList<Patient> patients = new ArrayList<Patient>();
		
		for (Node n : list) {
			Patient patient = this.resolvePatientFields(n);
			
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
