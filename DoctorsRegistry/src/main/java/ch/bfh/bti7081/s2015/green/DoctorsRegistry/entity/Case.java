package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import java.util.ArrayList;

public class Case {
	
	private int id = -1;
	private long timestamp = -1;
	private ArrayList<Appointment> alAppnmts = null;
	private User doctor = null;
	private Patient patient = null;

	public ArrayList<Appointment> getAlAppnmt() {
		return alAppnmts;
	}

	public void setAlAppnmt(ArrayList<Appointment> alAppnmt) {
		this.alAppnmts = alAppnmt;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
