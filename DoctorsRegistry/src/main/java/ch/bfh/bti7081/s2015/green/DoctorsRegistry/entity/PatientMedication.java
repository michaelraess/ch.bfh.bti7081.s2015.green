package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class PatientMedication {
	
	private int id = -1;
	private String name = "";
	private int dose = -1;
	private int appointmentId = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDose() {
		return dose;
	}
	public void setDose(int dose) {
		this.dose = dose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	

}
