package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import java.util.ArrayList;

public class Case {
	
	private int id = -1;
	private String createdDate = null;
	private String createdTime = null;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
