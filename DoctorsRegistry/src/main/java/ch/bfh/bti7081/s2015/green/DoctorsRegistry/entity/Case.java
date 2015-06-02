package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

import java.util.ArrayList;

public class Case {
	
	private int id = -1;
	private long created = -1;
	private ArrayList<Appointment> alAppnmts = null;
	private int userId = -1;
	private int patientId = -1;

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public ArrayList<Appointment> getAlAppnmt() {
		return alAppnmts;
	}

	public void setAlAppnmt(ArrayList<Appointment> alAppnmt) {
		this.alAppnmts = alAppnmt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
