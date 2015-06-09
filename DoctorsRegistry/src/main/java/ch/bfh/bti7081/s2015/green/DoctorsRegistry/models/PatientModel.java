package ch.bfh.bti7081.s2015.green.DoctorsRegistry.models;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.neo4j.graphdb.Node;

import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.Patient;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoId;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity.annotations.neoProperty;
import ch.bfh.bti7081.s2015.green.DoctorsRegistry.models.helpers.neoActions;

public class PatientModel extends DefaultModel implements neoActions<Patient> {

	private final String LABEL = "User";

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

}
