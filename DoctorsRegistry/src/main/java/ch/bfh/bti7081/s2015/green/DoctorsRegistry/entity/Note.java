package ch.bfh.bti7081.s2015.green.DoctorsRegistry.entity;

public class Note {
	
	private int id = -1;
	private int appointmentId = -1;
	private String noteText = "";

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
